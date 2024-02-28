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
import dev.jasperwiese.roastingCRM.utilities.validators.ClientValidator;
import dev.jasperwiese.roastingCRM.utilities.validators.RoastingProfileValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private final ClientValidator clientValidator;

    public RoastingProfileServiceImpl(
            RoastingProfileRepository roastingProfileRepository,
            ClientRepository clientRepository,
            RoastingProfileMapper roastingProfileMapper,
            ClientRoastingProfileRepository clientRoastingProfileRepository,
            RoastingProfileValidator roastingProfileValidator,
            ClientValidator clientValidator) {
        this.roastingProfileRepository = roastingProfileRepository;
        this.clientRepository = clientRepository;
        this.roastingProfileMapper = roastingProfileMapper;
        this.clientRoastingProfileRepository = clientRoastingProfileRepository;
        this.roastingProfileValidator = roastingProfileValidator;
        this.clientValidator = clientValidator;
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

    @Override
    public List<RoastingProfileDto> getAllRoastingProfiles() {
        List<RoastingProfile> roastingProfileList = roastingProfileRepository.findAll();
        List<RoastingProfileDto> roastingProfileDtoList = new ArrayList<>();
        for (RoastingProfile roastingProfile : roastingProfileList) {
            RoastingProfileDto roastingProfileDto = roastingProfileMapper.mapToDto(roastingProfile);
            roastingProfileDtoList.add(roastingProfileDto);
        }
        return roastingProfileDtoList;
    }

    @Transactional
    @Override
    public RoastingProfileDto getRoastingProfileById(String roastingProfileId) {

        boolean roastingProfileExists = roastingProfileValidator.validateIfRoastingProfileExists(roastingProfileId);
        if(!roastingProfileExists) {
            throw new RuntimeException("Roasting profile does not exist");
        }
        Optional<RoastingProfile> roastingProfile = roastingProfileRepository.findById(UUID.fromString(roastingProfileId));
        if(roastingProfile.isEmpty()) {
            throw new RuntimeException("Roasting profile does not exist");
        }
        return roastingProfileMapper.mapToDto(roastingProfile.get());


    }

    @Override
    public List<RoastingProfileDto> getAllRoastingProfilesOfClient(String clientId) {
        List<ClientRoastingProfiles> clientRoastingProfilesList = clientRoastingProfileRepository.findClientRoastingProfilesByClientClientId(UUID.fromString(clientId));
        List<RoastingProfileDto> roastingProfileDtoList = new ArrayList<>();
        for (ClientRoastingProfiles clientRoastingProfiles : clientRoastingProfilesList) {
            RoastingProfile roastingProfile = clientRoastingProfiles.getRoastingProfile();
            RoastingProfileDto roastingProfileDto = roastingProfileMapper.mapToDto(roastingProfile);
            roastingProfileDtoList.add(roastingProfileDto);
        }
        return roastingProfileDtoList;
    }

    @Override
    public void deleteRoastingProfileById(String roastingProfileId) {
      boolean roastingProfileExists = roastingProfileValidator.validateIfRoastingProfileExists(roastingProfileId);
      if(!roastingProfileExists) {
          throw new RuntimeException("Roasting profile does not exist");
      }
      roastingProfileRepository.deleteById(UUID.fromString(roastingProfileId));
    }

}
