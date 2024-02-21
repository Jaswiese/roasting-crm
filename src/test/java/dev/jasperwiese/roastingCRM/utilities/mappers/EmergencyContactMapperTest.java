package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.EmergencyContactDto;
import dev.jasperwiese.roastingCRM.entity.EmergencyContact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmergencyContactMapperTest {
    private EmergencyContactMapper underTest;

    private EmergencyContact emergencyContact;

    private EmergencyContactDto emergencyContactDto;

    @BeforeEach
    void setUp() {
        underTest = new EmergencyContactMapper();

        UUID emergencyContactId = UUID.fromString("2db61d0a-a6e4-4d89-a243-e7ee608d2047");
         emergencyContact = new EmergencyContact(
                emergencyContactId,
                "Bob",
                "Geese",
                "bob.geese@hoola.com",
                "+278574466978",
                "father"
                );
         emergencyContactDto = new EmergencyContactDto(
                null,
                "Bob",
                "Geese",
                "bob.geese@hoola.com",
                "+278574466978",
                "father"
        );
    }
    @Test
    void itShouldMapToDto() {
        //Given
        //When
        EmergencyContactDto dto = underTest.mapToDto(emergencyContact);
        //Then
        assertThat(dto).isInstanceOf(EmergencyContactDto.class);
        assertThat(dto.getEmergencyContactId()).isEqualTo("2db61d0a-a6e4-4d89-a243-e7ee608d2047");
        assertThat(dto.getFirstName()).isEqualTo("Bob");
        assertThat(dto.getLastName()).isEqualTo("Geese");
        assertThat(dto.getEmail()).isEqualTo("bob.geese@hoola.com");
        assertThat(dto.getPhoneNumber()).isEqualTo("+278574466978");
        assertThat(dto.getRelationship()).isEqualTo("father");

    }

    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        EmergencyContact entity = underTest.mapToEntity(emergencyContactDto);
        //Then
        assertThat(entity).isInstanceOf(EmergencyContact.class);
        assertThat(entity.getEmergencyContactId()).isNull();
        assertThat(entity.getFirstName()).isEqualTo("Bob");
        assertThat(entity.getLastName()).isEqualTo("Geese");
        assertThat(entity.getEmail()).isEqualTo("bob.geese@hoola.com");
        assertThat(entity.getPhoneNumber()).isEqualTo("+278574466978");
        assertThat(entity.getRelationship()).isEqualTo("father");
    }

    @Test void itShouldAssignAnEmergencyContactIdToTheEntity(){
        //Given
        EmergencyContactDto dto = emergencyContactDto;
        dto.setEmergencyContactId("2db61d0a-a6e4-4d89-a243-e7ee608d2047");
        //When
        EmergencyContact entity = underTest.mapToEntity(dto);
        //Then
        assertThat(entity).isInstanceOf(EmergencyContact.class);
        assertThat(entity.getEmergencyContactId()).isEqualTo(UUID.fromString("2db61d0a-a6e4-4d89-a243-e7ee608d2047"));
        assertThat(entity.getFirstName()).isEqualTo("Bob");
        assertThat(entity.getLastName()).isEqualTo("Geese");
        assertThat(entity.getEmail()).isEqualTo("bob.geese@hoola.com");
        assertThat(entity.getPhoneNumber()).isEqualTo("+278574466978");
        assertThat(entity.getRelationship()).isEqualTo("father");
    }
}