package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
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
public class RoastingProfileDto {

    private String roastingProfileId;

    @NotEmpty(message = "profile name can not be empty.")
    @NotNull(message = "profile name can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "profile name must be between 2 and 255 characters long."
    )
    private String profileName;

    @NotEmpty(message = "roaster model ca not be empty.")
    @NotNull(message = "roaster model can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "roaster model must be between 2 and 255 characters long."
    )
    private String roasterModel;

    @Valid
    private GreenBeansDto greenBeansDto;
    @Valid
    private TargetTemperatureDto targetTemperatureDto;
    @Valid
    private TimeIntervalsDto timeIntervalsDto;
    @Valid
    private AirFlowSettingsDto airFlowSettingsDto;

    @Size(
            min = 2,
            max = 255,
            message = "notes must be between 2 and 255 characters long."
    )
    private String notes;
}
