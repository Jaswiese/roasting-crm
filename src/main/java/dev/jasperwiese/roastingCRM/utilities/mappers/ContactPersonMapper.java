package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactPersonMapper {
    private ContactDetailsMapper contactDetailsMapper;

    public ContactPersonMapper(ContactDetailsMapper contactDetailsMapper) {
        this.contactDetailsMapper = contactDetailsMapper;
    }

    public ContactPerson mapToEntity(ContactPersonDto contactPersonDto) {
        ContactPerson contactPerson = new ContactPerson();
        if(contactPersonDto.getContactPersonId() != null && !contactPersonDto.getContactPersonId().isEmpty()){
            System.out.println("hit mapToEntity ContactPerson");
            contactPerson.setContactPersonId(UUID.fromString(contactPersonDto.getContactPersonId()));
        }
        contactPerson.setFirstName(contactPersonDto.getFirstName());
        contactPerson.setLastName(contactPersonDto.getLastName());
        contactPerson.setPositionHeld(contactPersonDto.getPositionHeld());
        contactPerson.setContactDetails(contactDetailsMapper.mapToEntity(contactPersonDto.getContactDetailsDto()));
        return contactPerson;
    }

    public ContactPersonDto mapToDto(ContactPerson contactPerson) {
        ContactPersonDto contactPersonDto = new ContactPersonDto();
        contactPersonDto.setContactPersonId(contactPerson.getContactPersonId().toString());
        contactPersonDto.setFirstName(contactPerson.getFirstName());
        contactPersonDto.setLastName(contactPerson.getLastName());
        contactPersonDto.setPositionHeld(contactPerson.getPositionHeld());
        contactPersonDto.setContactDetailsDto(contactDetailsMapper.mapToDto(contactPerson.getContactDetails()));
        return contactPersonDto;
    }
}
