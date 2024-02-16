package dev.jasperwiese.roastingCRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TargetTemperatureDto {
    private String targetTemperatureId;
    private String preheat;
    private String firstCrack;
    private String development;
    private String drop;
}
