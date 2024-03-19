package dev.jasperwiese.roastingCRM.dto;

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
public class TargetTemperatureDto {

    private String targetTemperatureId;

    @NotEmpty(message = "preheat cannot be empty")
    @NotNull(message = "preheat cannot be null")
    private String preheat;

    @Notn
    private String firstCrack;
    private String development;
    private String drop;
}
