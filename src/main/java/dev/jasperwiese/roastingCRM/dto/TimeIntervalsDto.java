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
public class TimeIntervalsDto {

    private String timeIntervalsId;

    @NotEmpty(message = "preheat can not be empty.")
    @NotNull(message = "preheat can not be null.")
    @Pattern(
            regexp = "/^\\d+$/",
            message = "preheat can only contain digits. This is due to the fact that this reflects minutes."
    )
    private String preheat;

    @NotEmpty(message = "first crack can not be empty.")
    @NotNull(message = "first crack can not be null.")
    @Pattern(
            regexp = "/^\\d+$/",
            message = "first crack can only contain digits. This is due to the fact that this reflects minutes."
    )
    private String firstCrack;

    @NotEmpty(message = "development can not be empty.")
    @NotNull(message = "development can not be null.")
    @Pattern(
            regexp = "/^\\d+$/",
            message = "development can only contain digits. This is due to the fact that this reflects minutes."
    )
    private String development;

    @NotEmpty(message = "total can not be empty.")
    @NotNull(message = "total can not null.")
    @Pattern(
            regexp = "/^\\d+$/",
            message = "total can only contain digits. This is due to the fact that this reflects minutes."
    )
    private String total;
}
