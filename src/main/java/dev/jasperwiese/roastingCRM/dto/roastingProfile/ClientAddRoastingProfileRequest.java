package dev.jasperwiese.roastingCRM.dto.roastingProfile;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientAddRoastingProfileRequest {

    private String clientId;

    @NotEmpty(message = "profile name can not be empty.")
    @NotNull(message = "profile name can not be empty.")
    @Size(
            min = 2,
            max = 255,
            message = "profile name must be between 2 and 255 characters long."
    )
    private String profileName;

    @NotEmpty(message = "notes can not be empty")
    @NotNull(message = "notes can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "notes must be between 2 and 255 characters long."
    )
    private String notes;

    @Valid
    private RoastingProfileDto roastingProfileDto;
}
