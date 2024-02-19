package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientRoastingProfileMapper {
    private RoastingProfileMapper roastingProfileMapper;

    public ClientRoastingProfileMapper(RoastingProfileMapper roastingProfileMapper) {
        this.roastingProfileMapper = roastingProfileMapper;
    }


}
