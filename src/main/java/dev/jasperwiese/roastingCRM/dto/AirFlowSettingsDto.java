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
public class AirFlowSettingsDto {

    private String airFlowSettingsId;

    @NotEmpty(message = "low cannot be empty")
    @NotNull(message = "low cannot be null")
    private String low;

    @NotEmpty(message = "medium cannot be empty")
    @NotNull(message = "medium cannot be null")
    private String medium;

    @NotEmpty(message = "high cannot be empty")
    @NotNull(message = "high cannot be null")
    private String high;
}
