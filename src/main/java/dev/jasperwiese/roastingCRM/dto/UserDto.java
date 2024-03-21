package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
* Validation checks are made on the UserDto, as to check the JSON response body of relevant post requests in the
* UserController and other relevant controllers that contain the UserDto as part of their post request.*/
public class UserDto {

    private String userId;

    @NotEmpty(message = "user needs to indicate verification status.")
    private Boolean verified;

    @NotEmpty(message = "First name may not be empty.")
    @Size(
            min = 2,
            max = 32,
            message = "First name must be between 2 and 32 characters long."
    )
    private String firstName;

    @NotEmpty(message = "Last name may not be empty.")
    @Size(
            min = 1,
            max = 32,
            message = "Last name must be between 1 and 32 characters long."
    )
    private String lastName;
    /*
    * The Regex pattern of ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[-+_!@#$%^&*.,?]).{12,}$
    * Checks that the password of the submitted request follows the bare-minimum of security requirements regarding, a secure password.
    * By forcing the submission to contain at least One Capital letter, One lowercase letter, One number and One Symbol. Further,
    * the minimum characters for submission is 12 characters long.*/
    //TODO: add JSON property restriction on returning with the request response body.
    @Pattern(
            regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*.,?]).{12,}$/",
            message = "Passwords must contain at least; One capital letter," +
                    " One lowercase letter, One number, One Symbol and be a minimum of 12 characters."
    )
    private String password;

    @NotEmpty(message = "Position cannot be empty.")
    private String position;

    @NotEmpty(message = "Role cannot be empty.")
    private String role;
    //Checks that the included ContactDetailsDto nested Bean is a valid ContactDetailsDto Bean - see ContactDetailsDto for validation checks.
    @Valid
    private ContactDetailsDto contactDetailsDto;

    @Valid
    private EmergencyContactDto emergencyContactDto;

    @NotEmpty(message = "a User address must be provided and cannot be empty.")
    private List<AddressDto> addressDtoList;
}
