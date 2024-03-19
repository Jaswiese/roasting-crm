package dev.jasperwiese.roastingCRM.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GreenBeansDto {

    private String greenBeansId;

    @NotEmpty(message = "field can not be empty.")
    @NotNull(message = "field can not be null.")
    @Min(
            value = 2,
            message = "Character length minimum is 2 characters."
    )
    @Max(
        value = 255,
        message = "Character length maximum is 255 characters."
    )
    private String field;

    @NotEmpty(message = "region can not be empty.")
    @NotNull(message = "region can not be null.")
    @Min(
            value = 2,
            message = "region minimum character length is 2 characters."
    )
    @Max(
            value = 255,
            message = "region maximum character length is 255 characters."
    )
    private String region;

    @NotEmpty(message = "grade can not be empty.")
    @NotNull(message = "grade can not be null.")
    @Min(
            value = 2,
            message = "grade minimum character length is 2 characters."
    )
    @Max(
            value = 255,
            message = "grade maximum character length is 255 characters."
    )
    private String grade;

    @NotEmpty(message = "flavour can not be empty.")
    @NotNull(message = "flavour can not be null.")
    @Min(
            value = 2,
            message = "flavour minimum character length is 2 characters."
    )
    @Max(
            value = 255,
            message = "flavour maximum character length is 255 characters."
    )
    private String flavour;

    @NotEmpty(message = "body can not be empty.")
    @NotNull(message = "body can not be null.")
    @Min(
            value = 2,
            message = "body minimum character length is 2 characters."
    )
    @Max(
            value = 255,
            message = "body maximum character length is 255 characters."
    )
    private String body;

    @NotEmpty(message = "acidity can not be empty.")
    @NotNull(message = "acidity can not be null.")
    @Min(
            value = 2,
            message = "acidity minimum character length is 2 characters."
    )
    @Max(
            value = 255,
            message = "acidity maximum character length is 255 characters."
    )
    private String acidity;

    @NotEmpty(message = "process can not be empty.")
    @NotNull(message = "process can not be null.")
    @Min(
            value = 2,
            message = "process minimum character length is 2 characters."
    )
    @Max(
            value = 255,
            message = "process maximum character length is 255 characters."
    )
    private String process;

    @NotEmpty(message = "moisture can not be empty.")
    @NotNull(message = "moisture can not be null.")
    @Pattern(
            regexp = "\"^(100\\\\.00%|\\\\d{1,2}(\\\\.\\\\d{1,2})?%)$\"",
            message = "moisture has an upper limit of 100.00%, " +
                    "and must contain the special character % and can only consist of digits.. "
    )
    private String moisture;

    @NotEmpty(message = "packaging can not be empty.")
    @NotNull(message = "packaging can not be null.")
    @Min(
            value = 2,
            message = "packaging has a minimum character length of 2."
    )
    @Max(
            value = 255,
            message = "packaging has a maximum of  character length of 255."
    )
    private String packaging;

    @NotEmpty(message = "notes can not be empty.")
    @NotNull(message = "notes can not be null.")
    @Min(
            value = 2,
            message = "notes has a minimum character length of 2 characters."
    )
    @Max(
            value = 255,
            message = "notes has a maximum character length of 255 characters."
    )
    private String notes;
}
