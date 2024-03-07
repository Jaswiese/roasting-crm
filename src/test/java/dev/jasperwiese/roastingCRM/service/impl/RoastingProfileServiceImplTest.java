package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.dto.roastingProfile.ClientAddRoastingProfileRequest;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

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

    private RoastingProfile roastingProfile;
    private RoastingProfileDto roastingProfileDto;
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
       /*Both of roastingProfile entity & roastingProfileDto will be the exact same as another,
        due to the frequent mapping between the dto & entity in the logic.
       * */
       //Initialising a RoastingProfile entity for reuse in the unit tests below
       roastingProfile = RoastingProfile.builder()
               .roastingProfileId(UUID.fromString("581213cb-37d6-4124-ac5f-7d694f7bd570"))
               .profileName("sample profile name")
               .roasterModel("roaster model sample")
               .notes("sample notes")
               .greenBeans(
                       GreenBeans.builder()
                               .greenBeansId(UUID.fromString("6d4cbbde-8560-4d26-a7ac-11f392c49bc0"))
                               .field("sample field")
                               .region("Uganda")
                               .grade("AA")
                               .flavour("Bold")
                               .body("Full")
                               .acidity("Low")
                               .process("Shelled")
                               .moisture("10%")
                               .packaging("Box")
                               .notes("High Quality Bean.")
                               .build()
               )
               .targetTemperature(
                       TargetTemperature.builder()
                               .targetTemperatureId(UUID.fromString("625fd101-d771-4420-b46a-6a787df091ac"))
                               .preheat("180")
                               .firstCrack("200")
                               .development("250")
                               .dropTemperature("260")
                               .build()
               )
               .timeIntervals(
                       TimeIntervals.builder()
                               .timeIntervalsId(UUID.fromString("9c717e6e-237d-4c21-9758-4d4b9d43beb0"))
                               .preheat("180")
                               .firstCrack("300")
                               .development("500")
                               .total("650")
                               .build()
               )
               .airflowSettings(
                       AirflowSettings.builder()
                               .airflowSettingsId(UUID.fromString("9c35be37-a922-4a38-8c10-22ab7cf6f6e7"))
                               .low("50")
                               .medium("100")
                               .high("200")
                               .build()
               )
               .build();
       //Initialising a RoastingProfileDto for reuse in the unit tests below.
        roastingProfileDto = RoastingProfileDto.builder()
                .roastingProfileId("581213cb-37d6-4124-ac5f-7d694f7bd570")
                .profileName("sample profile name")
                .roasterModel("roaster model sample")
                .notes("sample notes")
                .greenBeansDto(
                        GreenBeansDto.builder()
                                .greenBeansId("6d4cbbde-8560-4d26-a7ac-11f392c49bc0")
                                .field("sample field")
                                .region("Uganda")
                                .grade("AA")
                                .flavour("Bold")
                                .body("Full")
                                .acidity("Low")
                                .process("Shelled")
                                .moisture("10%")
                                .packaging("Box")
                                .notes("High Quality Bean.")
                                .build()
                )
                .targetTemperatureDto(
                        TargetTemperatureDto.builder()
                                .targetTemperatureId("625fd101-d771-4420-b46a-6a787df091ac")
                                .preheat("180")
                                .firstCrack("200")
                                .development("250")
                                .drop("260")
                                .build()
                )
                .timeIntervalsDto(
                        TimeIntervalsDto.builder()
                                .timeIntervalsId("9c717e6e-237d-4c21-9758-4d4b9d43beb0")
                                .preheat("180")
                                .firstCrack("300")
                                .development("500")
                                .total("650")
                                .build()
                )
                .airFlowSettingsDto(
                        AirFlowSettingsDto.builder()
                                .airFlowSettingsId("9c35be37-a922-4a38-8c10-22ab7cf6f6e7")
                                .low("50")
                                .medium("100")
                                .high("200")
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
    void itShouldNotSaveClientRoastingProfileIfClientDoesNotExist() {
        //Given
        given(clientRepository.findById(any())).willReturn(Optional.empty());
        //When
        assertThatThrownBy(() -> underTest.createClientRoastingProfile(clientAddRoastingProfileRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Client does not exist");
        //Then
        then(clientRepository).shouldHaveNoMoreInteractions();
        then(roastingProfileRepository).shouldHaveNoInteractions();
        then(clientRoastingProfileRepository).shouldHaveNoInteractions();
    }

    @Test
    void itShouldGetAllRoastingProfiles() {
        //Given
        //Singleton list given to the mock database call for the findAll()
        List<RoastingProfile> roastingProfileList = Collections.singletonList(
                RoastingProfile.builder()
                        .roastingProfileId(UUID.fromString("581213cb-37d6-4124-ac5f-7d694f7bd570"))
                        .profileName("sample profile name")
                        .roasterModel("roaster model sample")
                        .notes("sample notes")
                        .greenBeans(
                                GreenBeans.builder()
                                        .greenBeansId(UUID.fromString("6d4cbbde-8560-4d26-a7ac-11f392c49bc0"))
                                        .field("sample field")
                                        .region("Uganda")
                                        .grade("AA")
                                        .flavour("Bold")
                                        .body("Full")
                                        .acidity("Low")
                                        .process("Shelled")
                                        .moisture("10%")
                                        .packaging("Box")
                                        .notes("High Quality Bean.")
                                        .build()
                        )
                        .targetTemperature(
                                TargetTemperature.builder()
                                        .targetTemperatureId(UUID.fromString("625fd101-d771-4420-b46a-6a787df091ac"))
                                        .preheat("180")
                                        .firstCrack("200")
                                        .development("250")
                                        .dropTemperature("260")
                                        .build()
                        )
                        .timeIntervals(
                                TimeIntervals.builder()
                                        .timeIntervalsId(UUID.fromString("9c717e6e-237d-4c21-9758-4d4b9d43beb0"))
                                        .preheat("180")
                                        .firstCrack("300")
                                        .development("500")
                                        .total("650")
                                        .build()
                        )
                        .airflowSettings(
                                AirflowSettings.builder()
                                        .airflowSettingsId(UUID.fromString("9c35be37-a922-4a38-8c10-22ab7cf6f6e7"))
                                        .low("50")
                                        .medium("100")
                                        .high("200")
                                        .build()
                        )
                        .build()
        );
        // repository mocked for the database call of findAll()
        given(roastingProfileRepository.findAll()).willReturn(roastingProfileList);
        // roastingProfileMapper.mapToDto call mocked
        given(roastingProfileMapper.mapToDto(any(RoastingProfile.class)))
                .willReturn(RoastingProfileDto.builder().build());
        //When
        List<RoastingProfileDto> result = underTest.getAllRoastingProfiles();
        //Then
        //Checking that the correct calls are made to the database
        then(roastingProfileRepository).should().findAll();
        //Checking that only one call was made.
        then(roastingProfileRepository).shouldHaveNoMoreInteractions();
        //Ensuring that the size of the mocked result is the same as it's input.
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isInstanceOf(RoastingProfileDto.class);
    }

    @Test
    void itShouldGetRoastingProfileById() {
        //Given
        given(roastingProfileValidator.validateIfRoastingProfileExists(any())).willReturn(true);
        given(roastingProfileRepository.findById(any())).willReturn(Optional.of(roastingProfile));
        given(roastingProfileMapper.mapToDto(any(RoastingProfile.class))).willReturn(roastingProfileDto);
        //When
        RoastingProfileDto result = underTest.getRoastingProfileById("581213cb-37d6-4124-ac5f-7d694f7bd570");
        //Then
        then(roastingProfileValidator).shouldHaveNoMoreInteractions();
        then(roastingProfileRepository).shouldHaveNoMoreInteractions();
        then(roastingProfileMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void itShouldThrowRunTimeExceptionIfRoastingProfileValidatorReturnsAFalseValueAsRoastingProfileDoesNotExist(){
        //Given
        given(roastingProfileValidator.validateIfRoastingProfileExists(any(String.class))).willReturn(false);
        //When
        assertThatThrownBy(() -> underTest.getRoastingProfileById("roastingProfileId"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Roasting profile does not exist");
        //Then
        then(roastingProfileRepository).shouldHaveNoInteractions();
        then(roastingProfileMapper).shouldHaveNoInteractions();
    }

    @Test
    void itShouldThrowRunTimeExceptionIfFindByIdRepositoryCallReturnsAnEmptyOptional() {
        //Given
        given(roastingProfileValidator.validateIfRoastingProfileExists(any())).willReturn(true);
        given(roastingProfileRepository.findById(any())).willReturn(Optional.empty());
        //When
        assertThatThrownBy(() -> underTest.getRoastingProfileById("581213cb-37d6-4124-ac5f-7d694f7bd670"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Roasting profile does not exist");
        //Then
        then(roastingProfileRepository).shouldHaveNoMoreInteractions();
        then(roastingProfileMapper).shouldHaveNoInteractions();
    }

    @Test
    void itShouldGetAllRoastingProfilesOfClient() {
        //Given
        List<ClientRoastingProfiles> clientRoastingProfilesList = Arrays.asList(
                ClientRoastingProfiles.builder()
                        .id(new ClientRoastingProfilesPK(
                                        UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e"),
                                        UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e")
                                )
                        )
                        .roastingProfile(roastingProfile)
                        .build(),
                ClientRoastingProfiles.builder()
                        .id(new ClientRoastingProfilesPK(
                                        UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e"),
                                        UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e")
                                )
                        )
                        .roastingProfile(roastingProfile)
                        .build()
        );
        given(clientRoastingProfileRepository.findClientRoastingProfilesByClientClientId(any(UUID.class)))
                .willReturn(clientRoastingProfilesList);
        given(roastingProfileMapper.mapToDto(any(RoastingProfile.class))).willReturn(roastingProfileDto);
        //When
        List<RoastingProfileDto> result = underTest.getAllRoastingProfilesOfClient("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e");
        //Then
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isInstanceOf(RoastingProfileDto.class);
        assertThat(result.get(1)).isInstanceOf(RoastingProfileDto.class);
        verify(clientRoastingProfileRepository,
                times(1))
                .findClientRoastingProfilesByClientClientId(UUID.fromString("0a0bb313-75fd-4d3d-ac4d-568dd2cc615e"));
        verify(roastingProfileMapper,
                times(2))
                .mapToDto(any(RoastingProfile.class));

    }

    @Test
    void itShouldDeleteRoastingProfileById() {
        //Given
        given(roastingProfileValidator.validateIfRoastingProfileExists(any(String.class))).willReturn(true);
        doNothing().when(roastingProfileRepository).deleteById(any(UUID.class));
        //When
        underTest.deleteRoastingProfileById("581213cb-37d6-4124-ac5f-7d694f7bd570");
        //Then
        verify(roastingProfileRepository,
                times(1)).deleteById(UUID.fromString("581213cb-37d6-4124-ac5f-7d694f7bd570"));
    }

    @Test
    void itShouldNotDeleteRoastingProfileByIdIfTheRoastingProfileDoesNotExistsAndThrowARuntimeException() {
        //Given
        given(roastingProfileValidator.validateIfRoastingProfileExists(any(String.class))).willReturn(false);
        //When
        assertThatThrownBy(() -> underTest.deleteRoastingProfileById("581213cb-37d6-4124-ac5f-7d694f7bd580"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Roasting profile does not exist");
        //Then
        then(roastingProfileRepository).shouldHaveNoInteractions();
    }
}