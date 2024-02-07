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
public class RoastingProfile {

    private String roastingProfileId;

    private String profileName;

    private String roasterModel;

    private String beanId;

    private String targetTemperatureId;

    private String timeIntervalsId;

    private String airflowSettingsId;

    private String notes;
}
