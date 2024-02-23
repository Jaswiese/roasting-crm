package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.TargetTemperatureDto;
import dev.jasperwiese.roastingCRM.entity.TargetTemperature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TargetTemperatureMapperTest {
    private TargetTemperatureMapper underTest;
    private TargetTemperature targetTemperature;
    private TargetTemperatureDto targetTemperatureDto;

    @BeforeEach
    void setUp() {
        underTest = new TargetTemperatureMapper();
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
    }
    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        TargetTemperature result = underTest.mapToEntity(targetTemperatureDto);
        //Then
        assertThat(result).isInstanceOf(TargetTemperature.class);
        assertThat(result.getTargetTemperatureId()).isNull();
        assertThat(result.getPreheat()).isEqualTo(targetTemperatureDto.getPreheat());
        assertThat(result.getFirstCrack()).isEqualTo(targetTemperatureDto.getFirstCrack());
        assertThat(result.getDevelopment()).isEqualTo(targetTemperatureDto.getDevelopment());
        assertThat(result.getDropTemperature()).isEqualTo(targetTemperatureDto.getDrop());
    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        TargetTemperatureDto result = underTest.mapToDto(targetTemperature);
        //Then
        assertThat(result).isInstanceOf(TargetTemperatureDto.class);
        assertThat(result.getTargetTemperatureId()).isEqualTo("625fd101-d771-4420-b46a-6a787df091ac");
        assertThat(result.getPreheat()).isEqualTo(targetTemperature.getPreheat());
        assertThat(result.getFirstCrack()).isEqualTo(targetTemperature.getFirstCrack());
        assertThat(result.getDevelopment()).isEqualTo(targetTemperature.getDevelopment());
        assertThat(result.getDrop()).isEqualTo(targetTemperature.getDropTemperature());
    }

    @Test
    void itShouldAssignTargetTemperatureIdToTheEntityIfTargetTemperatureDtoHasTargetTemperatureId() {
        //Given
        UUID targetTemperatureId = UUID.fromString("625fd101-d771-4420-b46a-6a787df091ac");
        targetTemperatureDto.setTargetTemperatureId(String.valueOf(targetTemperatureId));
        //When
        TargetTemperature result = underTest.mapToEntity(targetTemperatureDto);
        //Then
        assertThat(result.getTargetTemperatureId()).isNotNull();
        assertThat(result.getTargetTemperatureId()).isEqualTo(targetTemperatureId);
    }
}