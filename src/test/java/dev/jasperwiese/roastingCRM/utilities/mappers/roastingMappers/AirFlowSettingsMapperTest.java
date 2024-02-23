package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.AirFlowSettingsDto;
import dev.jasperwiese.roastingCRM.entity.AirflowSettings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AirFlowSettingsMapperTest {

    private AirFlowSettingsMapper underTest;
    private AirflowSettings airflowSettings;
    private AirFlowSettingsDto airFlowSettingsDto;

    @BeforeEach
    void setUp() {
        underTest = new AirFlowSettingsMapper();
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
    }
    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        AirflowSettings result = underTest.mapToEntity(airFlowSettingsDto);
        //Then
        assertThat(result).isInstanceOf(AirflowSettings.class);
        assertThat(result.getLow()).isEqualTo(airFlowSettingsDto.getLow());
        assertThat(result.getMedium()).isEqualTo(airFlowSettingsDto.getMedium());
        assertThat(result.getHigh()).isEqualTo(airFlowSettingsDto.getHigh());
    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        AirFlowSettingsDto result = underTest.mapToDto(airflowSettings);
        //Then
        assertThat(result).isInstanceOf(AirFlowSettingsDto.class);
        assertThat(result.getLow()).isEqualTo(airflowSettings.getLow());
        assertThat(result.getMedium()).isEqualTo(airflowSettings.getMedium());
        assertThat(result.getHigh()).isEqualTo(airflowSettings.getHigh());
    }

    @Test
    void itShouldAssignAirflowSettingsIdToAirflowSettingsEntityIfAirflowSettingsDtoIdIsNotNullOrEmpty() {
        //Given
        airFlowSettingsDto.setAirFlowSettingsId("9c35be37-a922-4a38-8c10-22ab7cf6f6e7");
        //When
        AirflowSettings result = underTest.mapToEntity(airFlowSettingsDto);
        //Then
        assertThat(result.getAirflowSettingsId()).isNotNull();
        assertThat(result.getAirflowSettingsId()).isEqualTo(UUID.fromString(airFlowSettingsDto.getAirFlowSettingsId()));
    }
}