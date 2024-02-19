package dev.jasperwiese.roastingCRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirFlowSettingsDto {
    private String airFlowSettingsId;
    private String low;
    private String medium;
    private String high;
}
