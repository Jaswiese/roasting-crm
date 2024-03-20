package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
* */
public class TargetTemperatureDto {

    private String targetTemperatureId;

    @NotEmpty(message = "preheat cannot be empty.")
    @NotNull(message = "preheat cannot be null.")
    @Pattern(
            regexp = "\\d{1,3}",
            message = "pre heat can only contain digits and is limited to a length of 3 digits."
    )
    private String preheat;

    @NotEmpty(message = "first crack cannot be empty.")
    @NotNull(message = "first crack cannot be null.")
    @Pattern(
            regexp = "\\d{1,3}",
            message = "first crack can only contain digits and is limited to a length of 3 digits."
    )
    private String firstCrack;

    @NotEmpty(message = "development cannot be empty.")
    @NotNull(message = "development cannot be null.")
    @Pattern(
            regexp = "\\d{1,3}",
            message = "development can only contain digits and is limited to a length of 3 digits."
    )
    private String development;

    @NotEmpty(message = "drop cannot be empty.")
    @NotNull(message = "drop cannot be null.")
    @Pattern(
            regexp = "\\d{1,3}",
            message = "drop can only contain digits and is limited to a length of 3 digits."
    )
    private String drop;
}
