package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


class ContactDetailsMapperTest {

    private ContactDetailsMapper underTest;

    private ContactDetailsDto contactDetailsDto;

    private ContactDetails contactDetails;

    @BeforeEach
    void setUp() {
        underTest = new ContactDetailsMapper();
        UUID contactDetailsId = UUID.fromString("2db61d0a-a6e4-4d89-a243-e7ee608d2047");

        contactDetails = new ContactDetails(
                contactDetailsId,
                "beb@gmail.com",
                "beb@hoof.com",
                "+27654455466",
                "+27654455543"
        );

        contactDetailsDto = new ContactDetailsDto(
                null,
                "beb@gmail.com",
                "beb@hoof.com",
                "+27654455466",
                "+27654455543"
        );

    }

    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        ContactDetails entity = underTest.mapToEntity(contactDetailsDto);
        //Then
        assertThat(entity).isInstanceOf(ContactDetails.class);
        assertThat(entity.getContactDetailsId()).isNull();
        assertThat(entity.getEmail()).isEqualTo("beb@gmail.com");
        assertThat(entity.getBusinessEmail()).isEqualTo("beb@hoof.com");
        assertThat(entity.getWorkPhone()).isEqualTo("+27654455466");
        assertThat(entity.getPersonalPhone()).isEqualTo("+27654455543");

    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        ContactDetailsDto dto = underTest.mapToDto(contactDetails);
        //Then
        assertThat(dto).isInstanceOf(ContactDetailsDto.class);
        assertThat(dto.getContactDetailsId()).isEqualTo("2db61d0a-a6e4-4d89-a243-e7ee608d2047");
        assertThat(dto.getEmail()).isEqualTo(contactDetails.getEmail());
        assertThat(dto.getBusinessEmail()).isEqualTo(contactDetails.getBusinessEmail());
        assertThat(dto.getWorkPhone()).isEqualTo(contactDetails.getWorkPhone());
        assertThat(dto.getPersonalPhone()).isEqualTo(contactDetails.getPersonalPhone());
    }
    @Test
    void itShouldNotAssignContactDetailsIdIfDtoIdIsNull() {
        //Given
        ContactDetailsDto dto = contactDetailsDto;
        dto.setContactDetailsId("2db61d0a-a6e4-4d89-a243-e7ee608d2047");
        //When
        ContactDetails entity = underTest.mapToEntity(dto);
        //Then
        assertThat(entity).isInstanceOf(ContactDetails.class);
        assertThat(entity.getContactDetailsId()).isEqualTo(UUID.fromString("2db61d0a-a6e4-4d89-a243-e7ee608d2047"));
    }
}