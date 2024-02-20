package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.RoastingProfileDto;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;
import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
import dev.jasperwiese.roastingCRM.repository.ClientRepository;
import dev.jasperwiese.roastingCRM.repository.ClientRoastingProfileRepository;
import dev.jasperwiese.roastingCRM.repository.RoastingProfileRepository;
import dev.jasperwiese.roastingCRM.service.RoastingProfileService;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import dev.jasperwiese.roastingCRM.utilities.validators.RoastingProfileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class RoastingProfileServiceImpl implements RoastingProfileService {

    private final RoastingProfileRepository roastingProfileRepository;

    private final ClientRepository clientRepository;

    private final RoastingProfileMapper roastingProfileMapper;

    private final ClientRoastingProfileRepository clientRoastingProfileRepository;

    private final RoastingProfileValidator roastingProfileValidator;

    public RoastingProfileServiceImpl(
            RoastingProfileRepository roastingProfileRepository,
            ClientRepository clientRepository,
            RoastingProfileMapper roastingProfileMapper,
            ClientRoastingProfileRepository clientRoastingProfileRepository,
            RoastingProfileValidator roastingProfileValidator) {
        this.roastingProfileRepository = roastingProfileRepository;
        this.clientRepository = clientRepository;
        this.roastingProfileMapper = roastingProfileMapper;
        this.clientRoastingProfileRepository = clientRoastingProfileRepository;
        this.roastingProfileValidator = roastingProfileValidator;
    }
    @Transactional
    @Override
    public RoastingProfileDto createClientRoastingProfile(ClientAddRoastingProfileRequest clientAddRoastingProfileRequest) {
        RoastingProfileDto roastingProfileDto = clientAddRoastingProfileRequest.getRoastingProfileDto();
        roastingProfileDto.setRoastingProfileId(String.valueOf(UUID.randomUUID()));
        //check if client exists
        Optional<Client> clientOptional = clientRepository.findById(UUID.fromString(clientAddRoastingProfileRequest.getClientId()));
        if(!clientOptional.isPresent() && clientOptional.isEmpty()) {
            throw new RuntimeException();
        }
        RoastingProfile roastingProfile = roastingProfileRepository.save(roastingProfileMapper.mapToEntity(roastingProfileDto));
        //create ClientRoastingProfile
        ClientRoastingProfiles clientRoastingProfiles = ClientRoastingProfiles.builder()
                .id(new ClientRoastingProfilesPK(UUID.fromString(roastingProfileDto.getRoastingProfileId()), clientOptional.get().getClientId()))
                .profileName(clientAddRoastingProfileRequest.getProfileName())
                .client(clientOptional.get())
                .roastingProfile(roastingProfileMapper.mapToEntity(roastingProfileDto))
                .notes(clientAddRoastingProfileRequest.getNotes())
                .build();
        //save roastingProfile & save ClientRoastingProfiles
        ClientRoastingProfiles clientRoastingProfilesSaved = clientRoastingProfileRepository.save(clientRoastingProfiles);
        return roastingProfileDto;
    }

    @Transactional
    @Override
    public RoastingProfileDto getRoastingProfileById(String roastingProfileId) {
        RoastingProfile roastingProfile = roastingProfileValidator.validateIfRoastingProfileExists(roastingProfileId);
        return roastingProfileMapper.mapToDto(roastingProfile);
    }

    @Override
    public List<RoastingProfileDto> getAllRoastingProfilesOfClient(String clientId) {
        return null;
    }

    @Override
    public Integer deleteRoastingProfileById(String roastingProfileId) {
        return null;
    }

    @Override
    public Integer deleteAllRoastingProfilesOfClient(String clientId) {
        return null;
    }
}
