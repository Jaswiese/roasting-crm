package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;
import dev.jasperwiese.roastingCRM.entity.RoastingProfile;
import dev.jasperwiese.roastingCRM.entity.TimeIntervals;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.repository.ClientRepository;
import dev.jasperwiese.roastingCRM.repository.ClientRoastingProfileRepository;
import dev.jasperwiese.roastingCRM.repository.RoastingProfileRepository;
import dev.jasperwiese.roastingCRM.utilities.mappers.GreenbeansMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.AirFlowSettingsMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.TargetTemperatureMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.TimeIntervalsMapper;
import dev.jasperwiese.roastingCRM.utilities.validators.ClientValidator;
import dev.jasperwiese.roastingCRM.utilities.validators.RoastingProfileValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoastingProfileServiceImplTest {
    private RoastingProfileServiceImpl underTest;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private RoastingProfileRepository roastingProfileRepository;
    @Mock
    private ClientRoastingProfileRepository clientRoastingProfileRepository;
    @Mock
    private RoastingProfileMapper roastingProfileMapper;
    @Mock
    private RoastingProfileValidator roastingProfileValidator;
    @Mock
    private ClientValidator clientValidator;
    private ClientAddRoastingProfileRequest clientAddRoastingProfileRequest;
    private GreenbeansMapper greenbeansMapper;
    private AirFlowSettingsMapper airFlowSettingsMapper;
    private TargetTemperatureMapper targetTemperatureMapper;
    private TimeIntervalsMapper timeIntervalsMapper;

    @BeforeEach
    void setUp() {
        underTest = new RoastingProfileServiceImpl(
                roastingProfileRepository,
                clientRepository,
                roastingProfileMapper,
                clientRoastingProfileRepository,
                roastingProfileValidator,
                clientValidator
        );

       clientAddRoastingProfileRequest = ClientAddRoastingProfileRequest.builder()
               .profileName("Beans' Afternoon sunset")
               .notes("Stock Afternoon sunset")
               .clientId("de7f51fd-ebf9-43a3-b3f7-ff3358ec360b")
               .roastingProfileDto(
                       RoastingProfileDto.builder()
                               .profileName("Afternoon sunset")
                               .roasterModel("LaMazzorco")
                               .notes("Dark roast")
                               .greenBeansDto(
                                       GreenBeansDto.builder()
                                               .field("Hen")
                                               .region("Kenya")
                                               .grade("AA")
                                               .flavour("Sweet")
                                               .body("Bold")
                                               .acidity("low")
                                               .process("Barrel")
                                               .moisture("10%")
                                               .packaging("Card")
                                               .notes("ideal for dark roasting")
                                               .build()
                               )
                               .targetTemperatureDto(
                                       TargetTemperatureDto.builder()
                                               .preheat("120")
                                               .firstCrack("150")
                                               .development("200")
                                               .build()
                               )
                               .timeIntervalsDto(
                                       TimeIntervalsDto.builder()
                                               .preheat("60")
                                               .firstCrack("120")
                                               .development("180")
                                               .total("220")
                                               .build()
                               )
                               .airFlowSettingsDto(
                                       AirFlowSettingsDto.builder()
                                               .low("50")
                                               .medium("80")
                                               .high("100")
                                               .build()
                               )
                               .build()
               )
               .build();
    }

    @Test
    void itShouldCreateClientRoastingProfile() {
        //BDD Testing implemented here.
        //Given
        //Initialising Entity mappers used within the mocking for readability.
        greenbeansMapper = new GreenbeansMapper();
        timeIntervalsMapper = new TimeIntervalsMapper();
        targetTemperatureMapper = new TargetTemperatureMapper();
        airFlowSettingsMapper = new AirFlowSettingsMapper();
        //Mocking the database check that the client exists
        given(clientRepository.findById(any())).willReturn(Optional.of(Client.builder()
                .clientId(UUID.fromString(clientAddRoastingProfileRequest.getClientId()))
                .build()));
        //Mocking the roastingProfile save to the database.
        given(roastingProfileRepository.save(any(RoastingProfile.class))).willReturn(
                RoastingProfile.builder()
                        .roastingProfileId(
                                UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e")
                        )
                        .profileName(
                                clientAddRoastingProfileRequest
                                .getRoastingProfileDto()
                                .getProfileName()
                        )
                        .roasterModel(
                                clientAddRoastingProfileRequest
                                        .getRoastingProfileDto()
                                        .getRoasterModel()
                        )
                        .airflowSettings(
                                airFlowSettingsMapper.mapToEntity(
                                clientAddRoastingProfileRequest
                                        .getRoastingProfileDto()
                                        .getAirFlowSettingsDto()
                                )
                        )
                        .targetTemperature(
                                targetTemperatureMapper.mapToEntity(
                                 clientAddRoastingProfileRequest
                                         .getRoastingProfileDto()
                                         .getTargetTemperatureDto()
                                )
                        )
                        .timeIntervals(
                                timeIntervalsMapper.mapToEntity(
                                        clientAddRoastingProfileRequest
                                                .getRoastingProfileDto()
                                                .getTimeIntervalsDto()
                                )
                        )
                        .greenBeans(
                                greenbeansMapper.mapToEntity(
                                        clientAddRoastingProfileRequest
                                                .getRoastingProfileDto()
                                                .getGreenBeansDto()
                                )
                        )
                        .build()
        );
        given(roastingProfileMapper.mapToEntity(any())).willReturn(RoastingProfile.builder()
                .roastingProfileId(
                        UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e")
                )
                .profileName(
                        clientAddRoastingProfileRequest
                                .getRoastingProfileDto()
                                .getProfileName()
                )
                .roasterModel(
                        clientAddRoastingProfileRequest
                                .getRoastingProfileDto()
                                .getRoasterModel()
                )
                .airflowSettings(
                        airFlowSettingsMapper.mapToEntity(
                                clientAddRoastingProfileRequest
                                        .getRoastingProfileDto()
                                        .getAirFlowSettingsDto()
                        )
                )
                .targetTemperature(
                        targetTemperatureMapper.mapToEntity(
                                clientAddRoastingProfileRequest
                                        .getRoastingProfileDto()
                                        .getTargetTemperatureDto()
                        )
                )
                .timeIntervals(
                        timeIntervalsMapper.mapToEntity(
                                clientAddRoastingProfileRequest
                                        .getRoastingProfileDto()
                                        .getTimeIntervalsDto()
                        )
                )
                .greenBeans(
                        greenbeansMapper.mapToEntity(
                                clientAddRoastingProfileRequest
                                        .getRoastingProfileDto()
                                        .getGreenBeansDto()
                        )
                )
                .build()
        );

        //When
        underTest.createClientRoastingProfile(clientAddRoastingProfileRequest);
        //Then
        //Argument Capture used to see if the non repository logic has been successfully executed for the entity being saved.
        ArgumentCaptor<ClientRoastingProfiles> clientRoastingProfilesArgumentCaptor = ArgumentCaptor.forClass(ClientRoastingProfiles.class);
        then(clientRoastingProfileRepository).should().save(clientRoastingProfilesArgumentCaptor.capture());

        ClientRoastingProfiles clientRoastingProfilesArgumentValue = clientRoastingProfilesArgumentCaptor.getValue();

        assertThat(clientRoastingProfilesArgumentValue).isInstanceOf(ClientRoastingProfiles.class);
        assertThat(clientRoastingProfilesArgumentValue.getId().getClientId())
                .isEqualTo(UUID.fromString(clientAddRoastingProfileRequest.getClientId()));
        //RoastingProfile is not comparatively tested as it is set randomly in the service.
        assertThat(clientRoastingProfilesArgumentValue.getId().getRoastingProfileId()).isNotNull();

    }

    @Test
    void itShouldGetAllRoastingProfiles() {
        //Given
        //When
        //Then
    }

    @Test
    void itShouldGetRoastingProfileById() {
        //Given
        //When
        //Then
    }

    @Test
    void itShouldGetAllRoastingProfilesOfClient() {
        //Given
        //When
        //Then
    }

    @Test
    void itShouldDeleteRoastingProfileById() {
        //Given
        //When
        //Then
    }
}