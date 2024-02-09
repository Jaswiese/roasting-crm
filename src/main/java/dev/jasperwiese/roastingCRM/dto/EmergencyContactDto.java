package dev.jasperwiese.roastingCRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyContactDto {
    private String emergencyContactId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String relationship;
}
