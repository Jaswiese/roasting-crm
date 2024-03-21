package dev.jasperwiese.roastingCRM.dto.client;

import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactPersonDto {

    private String contactPersonId;

    @NotEmpty(message = "first name can not be empty.")
    @NotNull(message = "first name can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "first name has to be between 2 and 255 characters in length."
    )
    private String firstName;

    @NotEmpty(message = "last name can not be empty.")
    @NotNull(message = "last name can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "last name has to be between 2 and 255 characters in length."
    )
    private String lastName;

    @NotEmpty(message = "position held can not be empty.")
    @NotNull(message = "position held can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "position held has to be between 2 and 255 characters in length."
    )
    private String positionHeld;

    @Valid
    private ContactDetailsDto contactDetailsDto;
}
