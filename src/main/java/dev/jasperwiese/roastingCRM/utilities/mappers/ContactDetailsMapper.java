package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ContactDetailsMapper {

    public ContactDetails mapToEntity(ContactDetailsDto contactDto) {
        ContactDetails contactDetails = new ContactDetails();
        if(contactDto.getContactDetailsId() != null && !contactDto.getContactDetailsId().isEmpty()){
            contactDetails.setContactDetailsId(UUID.fromString(contactDto.getContactDetailsId()));
        }
        contactDetails.setEmail(contactDto.getEmail());
        contactDetails.setBusinessEmail(contactDto.getBusinessEmail());
        contactDetails.setWorkPhone(contactDto.getWorkPhone());
        contactDetails.setPersonalPhone(contactDto.getPersonalPhone());
        return contactDetails;
    }

    public ContactDetailsDto mapToDto(ContactDetails contactDetails) {
        ContactDetailsDto contactDto = ContactDetailsDto.builder()
                .contactDetailsId(contactDetails.getContactDetailsId().toString())
                .email(contactDetails.getEmail())
                .businessEmail(contactDetails.getBusinessEmail())
                .workPhone(contactDetails.getWorkPhone())
                .personalPhone(contactDetails.getPersonalPhone())
                .build();
        return contactDto;
    }
}
