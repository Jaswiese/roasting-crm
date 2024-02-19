package dev.jasperwiese.roastingCRM.dto;

import dev.jasperwiese.roastingCRM.entity.GreenBeans;
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
    private String profileName;
    private String roasterModel;
    private GreenBeansDto greenBeansDto;
    private TargetTemperatureDto targetTemperatureDto;
    private TimeIntervalsDto timeIntervalsDto;
    private AirFlowSettingsDto airFlowSettingsDto;
    private String notes;
}
