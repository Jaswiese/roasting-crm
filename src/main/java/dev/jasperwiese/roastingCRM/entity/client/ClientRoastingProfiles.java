package dev.jasperwiese.roastingCRM.entity.client;

import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "client_roasting_profiles")
public class ClientRoastingProfiles {

    @EmbeddedId
    private UUID clientId;

    @ManyToOne
    @MapsId("roastingProfileId")
    @JoinColumn(name = "roasting_profile_id")
    private RoastingProfile roastingProfile;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    private String profileName;

    private String notes;
}
