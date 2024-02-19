package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

    public List<ContactPerson> mapToEntityList(List<ContactPersonDto> contactPersonDtoList) {
        List<ContactPerson>  contactPersonList = new ArrayList<>();
        for (ContactPersonDto contactPersonDto : contactPersonDtoList) {
            contactPersonList.add(mapToEntity(contactPersonDto));
        }
        return contactPersonList;
    }

    public List<ContactPersonDto> mapToDtoList(List<ContactPerson> contactPersonList) {
        List<ContactPersonDto> contactPersonDtoList = new ArrayList<>();
        for(ContactPerson contactPerson : contactPersonList) {
            contactPersonDtoList.add(mapToDto(contactPerson));
        }
        return contactPersonDtoList;
    }
}
