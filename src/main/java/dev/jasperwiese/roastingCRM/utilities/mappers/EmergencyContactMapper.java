package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.EmergencyContactDto;
import dev.jasperwiese.roastingCRM.entity.EmergencyContact;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmergencyContactMapper {

    public EmergencyContactDto mapToDto(EmergencyContact emergencyContact) {
        EmergencyContactDto emergencyContactDto = new EmergencyContactDto();
        emergencyContactDto.setEmergencyContactId(String.valueOf(emergencyContact.getEmergencyContactId()));
        emergencyContactDto.setFirstName(emergencyContact.getFirstName());
        emergencyContactDto.setLastName(emergencyContact.getLastName());
        emergencyContactDto.setEmail(emergencyContact.getEmail());
        emergencyContactDto.setPhoneNumber(emergencyContact.getPhoneNumber());
        emergencyContactDto.setRelationship(emergencyContact.getRelationship());
        return emergencyContactDto;
    }

    public EmergencyContact mapToEntity(EmergencyContactDto emergencyContactDto) {
        return EmergencyContact.builder()
                .emergencyContactId(UUID.fromString(emergencyContactDto.getEmergencyContactId()))
                .firstName(emergencyContactDto.getFirstName())
                .lastName(emergencyContactDto.getLastName())
                .email(emergencyContactDto.getEmail())
                .phoneNumber(emergencyContactDto.getPhoneNumber())
                .relationship(emergencyContactDto.getRelationship())
                .build();
    }
}
