package dev.jasperwiese.roastingCRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private Boolean verified;
    private String firstName;
    private String lastName;
    private String password;
    private String position;
    private String role;
    private ContactDetailsDto contactDetailsDto;
    private EmergencyContactDto emergencyContactDto;
    private List<AddressDto> addressDtoList;
}
