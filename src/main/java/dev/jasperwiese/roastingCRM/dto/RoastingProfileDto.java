package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private String profileName;

    @NotEmpty(message = "roaster model ca not be empty.")
    @NotNull(message = "roaster model can not be null.")
    private String roasterModel;

    @Valid
    private GreenBeansDto greenBeansDto;
    @Valid
    private TargetTemperatureDto targetTemperatureDto;
    @Valid
    private TimeIntervalsDto timeIntervalsDto;
    @Valid
    private AirFlowSettingsDto airFlowSettingsDto;

    @Max(
            value = 256,
        message = "Maximum characters in roaster profile notes has cannot exceed 256 characters."
    )
    private String notes;
}
