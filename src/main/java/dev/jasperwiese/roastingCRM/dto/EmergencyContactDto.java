package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "First name may not be empty.")
    @Size(min = 2, max = 32, message = "First name must be between 2 and 32 characters long.")
    private String firstName;

    @NotEmpty(message = "Last name may not be empty.")
    @Size(min = 1, max = 32, message = "Last name must be between 1 and 32 characters long.")
    private String lastName;

    @NotEmpty(message = "email cannot be empty.")
    @Email(message = "email must be a valid email.")
    private String email;

    @NotEmpty(message = "phone number cannot be empty of an emergency contact.")
    @Pattern(regexp = "^\\d{11,15}$")
    private String phoneNumber;

    @NotEmpty(message = "relationship cannot be empty")
    private String relationship;
}
