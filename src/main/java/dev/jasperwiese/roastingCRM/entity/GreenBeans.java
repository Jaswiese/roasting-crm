package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class GreenBeans {
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
