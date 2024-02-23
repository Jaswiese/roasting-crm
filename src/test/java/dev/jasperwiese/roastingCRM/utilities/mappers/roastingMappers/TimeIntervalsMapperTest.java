package dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers;

import dev.jasperwiese.roastingCRM.dto.TimeIntervalsDto;
import dev.jasperwiese.roastingCRM.entity.TimeIntervals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TimeIntervalsMapperTest {

    private TimeIntervalsMapper underTest;
    private TimeIntervals timeIntervals;
    private TimeIntervalsDto timeIntervalsDto;

    @BeforeEach
    void setUp() {
        underTest = new TimeIntervalsMapper();
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
    }

    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        TimeIntervals result = underTest.mapToEntity(timeIntervalsDto);
        //Then
        assertThat(result).isInstanceOf(TimeIntervals.class);
        assertThat(result.getPreheat()).isEqualTo(timeIntervalsDto.getPreheat());
        assertThat(result.getFirstCrack()).isEqualTo(timeIntervalsDto.getFirstCrack());
        assertThat(result.getDevelopment()).isEqualTo(timeIntervalsDto.getDevelopment());
        assertThat(result.getTotal()).isEqualTo(timeIntervalsDto.getTotal());

    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        TimeIntervalsDto result = underTest.mapToDto(timeIntervals);
        //Then
        assertThat(result).isInstanceOf(TimeIntervalsDto.class);
        assertThat(result.getPreheat()).isEqualTo(timeIntervals.getPreheat());
        assertThat(result.getFirstCrack()).isEqualTo(timeIntervals.getFirstCrack());
        assertThat(result.getDevelopment()).isEqualTo(timeIntervals.getDevelopment());
        assertThat(result.getTotal()).isEqualTo(timeIntervals.getTotal());
    }
    @Test
    void  ItShouldAssignTimeIntervalsEntityIdIfTimeIntervalsDtoIdIsNotNullOrEmpty(){
        //Given
        UUID timeIntervalsId = UUID.fromString("9c717e6e-237d-4c21-9758-4d4b9d43beb0");
        timeIntervalsDto.setTimeIntervalsId(String.valueOf(timeIntervalsId));
        //When
        TimeIntervals result = underTest.mapToEntity(timeIntervalsDto);
        //Then
        assertThat(result.getTimeIntervalsId()).isNotNull();
        assertThat(result.getTimeIntervalsId()).isEqualTo(timeIntervalsId);
    }
}