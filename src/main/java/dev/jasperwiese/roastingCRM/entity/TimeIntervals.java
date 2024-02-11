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
@Table(name = "time_intervals")
public class TimeIntervals {

    @Id
    @Column(name = "time_intervals_id")
    private UUID timeIntervalsId;

    @Column(name = "preheat")
    private String preheat;

    @Column(name = "first_crack")
    private String firstCrack;

    @Column(name = "development")
    private String development;

    @Column(name = "total")
    private String total;
}
