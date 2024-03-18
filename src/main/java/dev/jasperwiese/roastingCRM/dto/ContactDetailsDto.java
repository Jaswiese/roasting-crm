package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//TODO: Add support for area code - > for phone numbers.
public class ContactDetailsDto {

    private String contactDetailsId;

    @NotEmpty(message = "email cannot be empty.")
    @Email(message = "email must be a valid email.")
    private String email;

    @NotEmpty(message = "business email cannot be empty.")
    @Email(message = "business email must be a valid email.")
    private String businessEmail;
    /*
    Regex pattern used for numbers limiting to only numbers and at a maximum length of 15 characters
    as per the https://en.wikipedia.org/wiki/E.164 recommendation for maximum length.
    *NOTE* This does include area code for future support.
     */
    @NotEmpty(message = "work phone number cannot be empty")
    @Pattern(regexp = "^\\d{11,15}$")
    private String workPhone;

    @NotEmpty(message = "personal phone number cannot be empty")
    @Pattern(regexp = "^\\d{11,15}$")
    private String personalPhone;
}
