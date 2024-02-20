package dev.jasperwiese.roastingCRM.service;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;

import java.util.List;

public interface RoastingProfileService {

    RoastingProfileDto createClientRoastingProfile(ClientAddRoastingProfileRequest clientAddRoastingProfileRequest);

    RoastingProfileDto getRoastingProfileById(String roastingProfileId);

    List<RoastingProfileDto> getAllRoastingProfilesOfClient(String clientId);

    Integer deleteRoastingProfileById(String roastingProfileId);

    Integer deleteAllRoastingProfilesOfClient(String clientId);
}
