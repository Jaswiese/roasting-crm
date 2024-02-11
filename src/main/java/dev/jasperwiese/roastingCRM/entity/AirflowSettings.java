package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "airflow_settings")
public class AirflowSettings {

    @Id
    @Column(name = "airflow_settings_id")
    private UUID airflowSettingsId;

    @Column(name = "low")
    private String low;

    @Column(name = "medium")
    private String medium;

    @Column(name = "high")
    private String high;
}
