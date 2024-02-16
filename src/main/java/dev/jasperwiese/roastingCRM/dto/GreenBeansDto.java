package dev.jasperwiese.roastingCRM.dto;

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

    private String field;

    private String region;

    private String grade;

    private String flavour;

    private String body;

    private String acidity;

    private String process;

    private String moisture;

    private String packaging;

    private String notes;
}
