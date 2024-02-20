package dev.jasperwiese.roastingCRM.service;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;

import java.util.List;

public interface RoastingProfileService {

    RoastingProfileDto createClientRoastingProfile(ClientAddRoastingProfileRequest clientAddRoastingProfileRequest);

    List<RoastingProfileDto> getAllRoastingProfiles();

    RoastingProfileDto getRoastingProfileById(String roastingProfileId);

    List<RoastingProfileDto> getAllRoastingProfilesOfClient(String clientId);

    void deleteRoastingProfileById(String roastingProfileId);

}
