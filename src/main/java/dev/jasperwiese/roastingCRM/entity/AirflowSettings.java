package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class AirflowSettings {
    private String airflowSettingsId;
    private String low;
    private String medium;
    private String high;
}
