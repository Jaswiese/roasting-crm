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
//TODO: Add JPA annotations
@Table
public class TargetTemperature {

    private String targetTemperatureId;

    private String preheat;

    private String firstCrack;

    private String development;

    private String drop;
}
