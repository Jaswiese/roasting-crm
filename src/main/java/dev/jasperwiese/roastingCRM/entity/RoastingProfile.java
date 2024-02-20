package dev.jasperwiese.roastingCRM.entity;

import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "roasting_profile")
public class RoastingProfile {

    @Id
    @Column(
            name = "roasting_profile_id",
            columnDefinition = "Binary(16)"
    )
    private UUID roastingProfileId;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "roaster_model")
    private String roasterModel;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private GreenBeans greenBeans;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private TargetTemperature targetTemperature;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private TimeIntervals timeIntervals;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private AirflowSettings airflowSettings;

    @Column(name = "notes")
    private String notes;

    @OneToMany(
            mappedBy = "roastingProfile",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientRoastingProfiles> clientRoastingProfiles = new ArrayList<>();

}
