package dev.jasperwiese.roastingCRM.dto.client;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientAddRoastingProfileRequest {
    private String clientId;
    private String profileName;
    private String notes;
    private RoastingProfileDto roastingProfileDto;
}
