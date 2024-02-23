package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.utilities.mappers.GreenbeansMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoastingProfileMapperTest {

    private RoastingProfileMapper underTest;
    private RoastingProfile roastingProfile;
    private RoastingProfileDto roastingProfileDto;
    private TimeIntervals timeIntervals;
    private TimeIntervalsDto timeIntervalsDto;
    private TargetTemperature targetTemperature;
    private TargetTemperatureDto targetTemperatureDto;
    private AirflowSettings airflowSettings;
    private AirFlowSettingsDto airFlowSettingsDto;
    private GreenBeans greenBeans;
    private GreenBeansDto greenBeansDto;

    @BeforeEach
    void setUp() {
        GreenbeansMapper greenbeansMapper = new GreenbeansMapper();
        TimeIntervalsMapper timeIntervalsMapper = new TimeIntervalsMapper();
        AirFlowSettingsMapper airFlowSettingsMapper = new AirFlowSettingsMapper();
        TargetTemperatureMapper targetTemperatureMapper = new TargetTemperatureMapper();
        underTest = new RoastingProfileMapper(targetTemperatureMapper, timeIntervalsMapper, airFlowSettingsMapper, greenbeansMapper);

        UUID timeIntervalsId = UUID.fromString("9c717e6e-237d-4c21-9758-4d4b9d43beb0");
        timeIntervals = TimeIntervals.builder()
                .timeIntervalsId(timeIntervalsId)
                .preheat("180")
                .firstCrack("300")
                .development("500")
                .total("650")
                .build();

        timeIntervalsDto = TimeIntervalsDto.builder()
                .preheat("180")
                .firstCrack("300")
                .development("500")
                .total("650")
                .build();

        UUID targetTemperatureId = UUID.fromString("625fd101-d771-4420-b46a-6a787df091ac");
        targetTemperature = TargetTemperature.builder()
                .targetTemperatureId(targetTemperatureId)
                .preheat("180")
                .firstCrack("200")
                .development("250")
                .dropTemperature("260")
                .build();
        targetTemperatureDto = TargetTemperatureDto.builder()
                .preheat("180")
                .firstCrack("200")
                .development("250")
                .drop("260")
                .build();

        UUID airflowSettingsId = UUID.fromString("9c35be37-a922-4a38-8c10-22ab7cf6f6e7");
        airflowSettings = AirflowSettings.builder()
                .airflowSettingsId(airflowSettingsId)
                .low("50")
                .medium("100")
                .high("200")
                .build();
        airFlowSettingsDto = AirFlowSettingsDto.builder()
                .low("50")
                .medium("100")
                .high("200")
                .build();

        UUID greenBeansId = UUID.fromString("6d4cbbde-8560-4d26-a7ac-11f392c49bc0");
        greenBeans = GreenBeans.builder()
                .greenBeansId(greenBeansId)
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
                .build();
        greenBeansDto = GreenBeansDto.builder()
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
                .build();

        UUID roastingProfileId = UUID.fromString("a970afea-9160-49b0-b90b-37f65937bd84");
        roastingProfile = RoastingProfile.builder()
                .roastingProfileId(roastingProfileId)
                .profileName("Light Roast Ugandan Sunshine")
                .roasterModel("Evvecco 500")
                .greenBeans(greenBeans)
                .targetTemperature(targetTemperature)
                .timeIntervals(timeIntervals)
                .airflowSettings(airflowSettings)
                .notes("Citrus notes")
                .build();

        roastingProfileDto = RoastingProfileDto.builder()
                .profileName("Light Roast Ugandan Sunshine")
                .roasterModel("Evvecco 500")
                .greenBeansDto(greenBeansDto)
                .targetTemperatureDto(targetTemperatureDto)
                .timeIntervalsDto(timeIntervalsDto)
                .airFlowSettingsDto(airFlowSettingsDto)
                .notes("Citrus notes")
                .build();


    }


    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        RoastingProfile result = underTest.mapToEntity(roastingProfileDto);
        //Then
        assertThat(result).isInstanceOf(RoastingProfile.class);
        assertThat(result.getRoastingProfileId()).isNull();
        assertThat(result.getProfileName()).isEqualTo(roastingProfileDto.getProfileName());
        assertThat(result.getRoasterModel()).isEqualTo(roastingProfileDto.getRoasterModel());
        assertThat(result.getGreenBeans()).isInstanceOf(GreenBeans.class);
        assertThat(result.getTargetTemperature()).isInstanceOf(TargetTemperature.class);
        assertThat(result.getTimeIntervals()).isInstanceOf(TimeIntervals.class);
        assertThat(result.getAirflowSettings()).isInstanceOf(AirflowSettings.class);
        assertThat(result.getNotes()).isEqualTo(roastingProfileDto.getNotes());
    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        RoastingProfileDto result = underTest.mapToDto(roastingProfile);
        //Then
        assertThat(result).isInstanceOf(RoastingProfileDto.class);
        assertThat(result.getRoastingProfileId()).isEqualTo(roastingProfile.getRoastingProfileId().toString());
        assertThat(result.getProfileName()).isEqualTo(roastingProfile.getProfileName());
        assertThat(result.getRoasterModel()).isEqualTo(roastingProfile.getRoasterModel());
        assertThat(result.getGreenBeansDto()).isInstanceOf(GreenBeansDto.class);
        assertThat(result.getTargetTemperatureDto()).isInstanceOf(TargetTemperatureDto.class);
        assertThat(result.getTimeIntervalsDto()).isInstanceOf(TimeIntervalsDto.class);
        assertThat(result.getAirFlowSettingsDto()).isInstanceOf(AirFlowSettingsDto.class);
        assertThat(result.getNotes()).isEqualTo(roastingProfile.getNotes());
    }

    @Test

    void itShouldAssignRoastingProfileIdToEntityIfRoastingProfileDtoIdIsNotNullOrEmpty(){
        //Given
        roastingProfileDto.setRoastingProfileId("a970afea-9160-49b0-b90b-37f65937bd84");
        //When
        RoastingProfile result = underTest.mapToEntity(roastingProfileDto);
        //Then
        assertThat(result).isInstanceOf(RoastingProfile.class);
        assertThat(result.getRoastingProfileId()).isEqualTo(UUID.fromString(roastingProfileDto.getRoastingProfileId()));

    }
}