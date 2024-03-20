package dev.jasperwiese.roastingCRM.dto.roastingProfile;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Min(
            value = 2,
            message = "profile name has a minimum of 2 character length."
    )
    @Max(
            value = 255,
            message = "profile name has a maximum of 255 character length."
    )
    private String profileName;

    @NotEmpty(message = "notes can not be empty")
    @NotNull(message = "notes can not be null.")
    @Min(
            value = 2,
            message = "notes has a minimum of 2 character length."
    )
    @Max(
            value = 255,
            message = "notes has a maximum of 255 character length."
    )
    private String notes;

    @Valid
    private RoastingProfileDto roastingProfileDto;
}
