package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.GreenBeansDto;
import dev.jasperwiese.roastingCRM.entity.GreenBeans;
import dev.jasperwiese.roastingCRM.utilities.mappers.GreenbeansMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GreenbeansMapperTest {

    private GreenbeansMapper underTest;

    @BeforeEach
    void setUp(){
        underTest = new GreenbeansMapper();
    }

    @Test
    void itShouldSuccessfullyReturnGreenbeansEntity() {
        GreenBeansDto greenBeansDto = GreenBeansDto.builder()
                .build();
        GreenBeans greenBeans = underTest.mapToEntity(greenBeansDto);
        assertNotNull(greenBeans);
        assertInstanceOf(GreenBeans.class, greenBeans);
    }

    @Test
    void itShouldSuccessfullyReturnGreenbeansDto() {
        //Given
        GreenBeans greenBeans = GreenBeans.builder()
                .build();
        //When
        GreenBeansDto greenBeansDto = underTest.mapToDto(greenBeans);
        //Then
        assertNotNull(greenBeansDto);
        assertInstanceOf(GreenBeansDto.class, greenBeansDto);
    }

    @Test
    void itShouldSetTheGreenBeansIdOfTheEntityIfTheDtoIdIsNotNull() {
        //Given
        GreenBeansDto greenBeansDto = GreenBeansDto.builder()
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
                .build();
        //When
        GreenBeans greenBeans = underTest.mapToEntity(greenBeansDto);
        //Then
        assertEquals(UUID.fromString("6d4cbbde-8560-4d26-a7ac-11f392c49bc0"), greenBeans.getGreenBeansId());
    }

}
