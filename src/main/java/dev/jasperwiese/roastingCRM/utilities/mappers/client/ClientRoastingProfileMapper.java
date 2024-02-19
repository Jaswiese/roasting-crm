package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRoastingProfileMapper {
    private final RoastingProfileMapper roastingProfileMapper;

    public ClientRoastingProfileMapper(RoastingProfileMapper roastingProfileMapper) {
        this.roastingProfileMapper = roastingProfileMapper;
    }

    public List<ClientRoastingProfiles> mapRoastingProfileDtoListToClientRoastingProfilesList(List<RoastingProfileDto> roastingProfileDtoList) {
        List<ClientRoastingProfiles> clientRoastingProfilesList = new ArrayList<>();
        for (RoastingProfileDto roastingProfileDto : roastingProfileDtoList) {
            RoastingProfile roastingProfile = roastingProfileMapper.mapToEntity(roastingProfileDto);
            ClientRoastingProfiles clientRoastingProfiles = ClientRoastingProfiles.builder()
                    .roastingProfile(roastingProfile)
                    .build();
            clientRoastingProfilesList.add(clientRoastingProfiles);
        }
        return clientRoastingProfilesList;
    }

    public List<RoastingProfileDto> mapClientRoastingProfilesListToRoastingProfileDtoList(List<ClientRoastingProfiles> clientRoastingProfilesList) {
        List<RoastingProfileDto> roastingProfileDtoList = new ArrayList<>();
        for (ClientRoastingProfiles clientRoastingProfiles : clientRoastingProfilesList) {
            RoastingProfileDto roastingProfileDto = roastingProfileMapper.mapToDto(clientRoastingProfiles.getRoastingProfile());
            roastingProfileDtoList.add(roastingProfileDto);
        }
        return roastingProfileDtoList;
    }

}
