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
    @Size(
            min = 2,
            max = 255,
            message = "field must be between 2 and 255 characters long."
    )
    private String field;

    @NotEmpty(message = "region can not be empty.")
    @NotNull(message = "region can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "region must be between 2 and 255 characters long."
    )
    private String region;

    @NotEmpty(message = "grade can not be empty.")
    @NotNull(message = "grade can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "grade must be between 2 and 255 characters long."
    )
    private String grade;

    @NotEmpty(message = "flavour can not be empty.")
    @NotNull(message = "flavour can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "flavour must be between 2 and 255 characters long."
    )
    private String flavour;

    @NotEmpty(message = "body can not be empty.")
    @NotNull(message = "body can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "body must be between 2 and 255 characters long."
    )
    private String body;

    @NotEmpty(message = "acidity can not be empty.")
    @NotNull(message = "acidity can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "acidity must be between 2 and 255 characters long."
    )
    private String acidity;

    @NotEmpty(message = "process can not be empty.")
    @NotNull(message = "process can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "process must be between 2 and 255 characters long."
    )
    private String process;

    @NotEmpty(message = "moisture can not be empty.")
    @NotNull(message = "moisture can not be null.")
    @Pattern(
            regexp = "^(100\\.00%|\\d{1,2}(\\.\\d{1,2})?%)$",
            message = "moisture has an upper limit of 100.00%, " +
                    "and must contain the special character % and can only consist of digits.. "
    )
    private String moisture;

    @NotEmpty(message = "packaging can not be empty.")
    @NotNull(message = "packaging can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "packaging must be between 2 and 255 characters long."
    )
    private String packaging;

    @NotEmpty(message = "notes can not be empty.")
    @NotNull(message = "notes can not be null.")
    @Size(
            min = 2,
            max = 255,
            message = "notes must be between 2 and 255 characters long."
    )
    private String notes;
}
