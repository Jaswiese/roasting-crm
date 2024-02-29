package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.repository.RoastingProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoastingProfileValidatorTest {

    private RoastingProfileValidator underTest;
    @Mock
    private RoastingProfileRepository roastingProfileRepositoryMock;

    private UUID roastingProfileId;

    @BeforeEach
    void setUp(){
        roastingProfileId = UUID.fromString("52aafe86-16b4-437a-bda4-5be0f8d67665");
        underTest = new RoastingProfileValidator(roastingProfileRepositoryMock);

    }
    @Test
    void itShouldValidateIfRoastingProfileExists() {
        //Given
        when(roastingProfileRepositoryMock.existsById(any(UUID.class))).thenReturn(true);
        //When
        boolean result = underTest.validateIfRoastingProfileExists(roastingProfileId.toString());
        //Then
        assertThat(result).isTrue();
    }

    @Test
    void itShouldReturnAFalseIfRoastingProfileDoesNotExist(){
        //Given
        when(roastingProfileRepositoryMock.existsById(any(UUID.class))).thenReturn(false);
        //When
        boolean result = underTest.validateIfRoastingProfileExists(roastingProfileId.toString());
        //Then
        assertThat(result).isFalse();
    }
}