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
@Table
public class TargetTemperature {
    @Id
    @Column(name = "target_temperature_id")
    private UUID targetTemperatureId;

    @Column(name = "preheat")
    private String preheat;

    @Column(name = "first_crack")
    private String firstCrack;

    @Column(name = "development")
    private String development;

    @Column(name = "drop")
    private String drop;
}
