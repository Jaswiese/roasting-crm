package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "low cannot be null")
    private String low;

    @NotNull(message = "medium cannot be null")
    private String medium;

    @NotNull(message = "high cannot be null")
    private String high;
}
