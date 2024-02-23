package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContactPersonMapperTest {

    private ContactPersonMapper underTest;

    private ContactDetailsMapper contactDetailsMapper;
    private ContactPerson contactPerson;
    private ContactPersonDto contactPersonDto;
    private ContactDetails contactDetails;
    private ContactDetailsDto contactDetailsDto;

    @BeforeEach
    void setUp(){
        contactDetailsMapper = new ContactDetailsMapper();
        underTest = new ContactPersonMapper(contactDetailsMapper);

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

        UUID contactPersonId = UUID.fromString("5e8cdf58-e42d-4927-8df6-f8936d984b9c");

        contactPerson = ContactPerson.builder()
                .contactPersonId(contactPersonId)
                .firstName("Jane")
                .lastName("Doe")
                .positionHeld("Chief Executive")
                .contactDetails(contactDetails)
                .build();

        contactPersonDto = ContactPersonDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .positionHeld("Chief Executive")
                .contactDetailsDto(contactDetailsDto)
                .build();

    }

    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        ContactPerson result = underTest.mapToEntity(contactPersonDto);
        //Then
        assertThat(result).isInstanceOf(ContactPerson.class);
        assertThat(result.getContactPersonId()).isNull();
        assertThat(result.getFirstName()).isEqualTo(contactPersonDto.getFirstName());
        assertThat(result.getLastName()).isEqualTo(contactPersonDto.getLastName());
        assertThat(result.getPositionHeld()).isEqualTo(contactPersonDto.getPositionHeld());
        assertThat(result.getContactDetails().getPersonalPhone()).isEqualTo(contactPersonDto.getContactDetailsDto().getPersonalPhone());
        assertThat(result.getContactDetails().getWorkPhone()).isEqualTo(contactPersonDto.getContactDetailsDto().getWorkPhone());
        assertThat(result.getContactDetails().getEmail()).isEqualTo(contactPersonDto.getContactDetailsDto().getEmail());
        assertThat(result.getContactDetails().getBusinessEmail()).isEqualTo(contactPersonDto.getContactDetailsDto().getBusinessEmail());
    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        ContactPersonDto result = underTest.mapToDto(contactPerson);
        //Then
        assertThat(result).isInstanceOf(ContactPersonDto.class);
        assertThat(result.getContactPersonId()).isEqualTo(contactPerson.getContactPersonId().toString());
        assertThat(result.getFirstName()).isEqualTo(contactPerson.getFirstName());
        assertThat(result.getLastName()).isEqualTo(contactPerson.getLastName());
        assertThat(result.getPositionHeld()).isEqualTo(contactPerson.getPositionHeld());
        assertThat(result.getContactDetailsDto().getContactDetailsId()).isEqualTo(contactPerson.getContactDetails().getContactDetailsId().toString());
        assertThat(result.getContactDetailsDto().getPersonalPhone()).isEqualTo(contactPerson.getContactDetails().getPersonalPhone());
        assertThat(result.getContactDetailsDto().getWorkPhone()).isEqualTo(contactPerson.getContactDetails().getWorkPhone());
        assertThat(result.getContactDetailsDto().getEmail()).isEqualTo(contactPerson.getContactDetails().getEmail());
        assertThat(result.getContactDetailsDto().getBusinessEmail()).isEqualTo(contactPerson.getContactDetails().getBusinessEmail());

    }

    @Test
    void itShouldMapToEntityList() {
        //Given
        List<ContactPersonDto> contactPersonDtoList = Collections.singletonList(contactPersonDto);
        //When
        List<ContactPerson> result = underTest.mapToEntityList(contactPersonDtoList);
        //Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isInstanceOf(ContactPerson.class);
    }

    @Test
    void itShouldMapToDtoList() {
        //Given
        List<ContactPerson> contactPersonList = Collections.singletonList(contactPerson);
        //When
        List<ContactPersonDto> result = underTest.mapToDtoList(contactPersonList);
        //Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isInstanceOf(ContactPersonDto.class);
    }

    @Test
    void itShouldAddContactPersonIdIfContactPersonDtoIdIsNotNullOrEmpty() {
        //Given
         contactPersonDto.setContactPersonId("5e8cdf58-e42d-4927-8df6-f8936d984b9c");
        //When
        ContactPerson result = underTest.mapToEntity(contactPersonDto);
        //Then
        assertThat(result).isInstanceOf(ContactPerson.class);
        assertThat(result.getContactPersonId()).isEqualTo(UUID.fromString(contactPersonDto.getContactPersonId()));
    }
}