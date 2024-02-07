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
public class ClientRoastingProfiles {

    private String clientId;

    private String roastingProfileId;

    private String profileName;

    private String notes;
}