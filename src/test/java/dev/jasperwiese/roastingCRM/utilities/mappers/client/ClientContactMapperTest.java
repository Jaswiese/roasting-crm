package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientContactPK;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactDetailsMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactPersonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientContactMapperTest {

    private ClientContactMapper underTest;
    private List<ClientContact> clientContactList;
    private List<ContactPersonDto> contactPersonDtoList;



    @BeforeEach
    void setUp() {
        ContactDetailsMapper contactDetailsMapper = new ContactDetailsMapper();
        ContactPersonMapper contactPersonMapper = new ContactPersonMapper(contactDetailsMapper);
        underTest = new ClientContactMapper(contactPersonMapper);

        contactPersonDtoList = Arrays.asList(
                ContactPersonDto.builder()
                        .firstName("Hettie")
                        .lastName("Smith")
                        .positionHeld("Chief Executive Officer")
                        .contactDetailsDto(
                                ContactDetailsDto.builder()
                                        .email("hettieandthesmiths@gmail.com")
                                        .businessEmail("hettie@coffee.com")
                                        .workPhone("+27734578456")
                                        .personalPhone("+27734476556")
                                        .build()
                        )
                        .build(),
                ContactPersonDto.builder()
                        .firstName("Bobbie")
                        .lastName("Percy")
                        .positionHeld("Supply Manager")
                        .contactDetailsDto(
                                ContactDetailsDto.builder()
                                        .email("bobperc@gmail.com")
                                        .businessEmail("supply@beans.com")
                                        .workPhone("+27604587333")
                                        .personalPhone("27604597567")
                                        .build()
                        )
                        .build()
        );

        clientContactList = Arrays.asList(
                ClientContact.builder()
                        .contactPerson(
                                ContactPerson.builder()
                                        .firstName("Hettie")
                                        .lastName("Smith")
                                        .positionHeld("Chief Executive Officer")
                                        .contactDetails(
                                                ContactDetails.builder()
                                                        .email("hettieandthesmiths@gmail.com")
                                                        .businessEmail("hettie@coffee.com")
                                                        .workPhone("+27734578456")
                                                        .personalPhone("+27734476556")
                                                        .build()
                                        )
                                        .build()
                        )
                                .build(),
                ClientContact.builder()
                        .contactPerson(
                                ContactPerson.builder()
                                        .firstName("Bobbie")
                                        .lastName("Percy")
                                        .positionHeld("Supply Manager")
                                        .contactDetails(
                                                ContactDetails.builder()
                                                        .email("bobperc@gmail.com")
                                                        .businessEmail("supply@beans.com")
                                                        .workPhone("+27604587333")
                                                        .personalPhone("27604597567")
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
        );
    }

    @Test
    void itShouldMapClientContactListToContactPersonDtoList() {
        //Given
        //Setting  UUIDs:
        UUID clientId = UUID.fromString("addd2700-278e-42e5-8ad3-578d12f6d548");
        UUID clientContactId = UUID.fromString("4d341bb6-519c-46ec-939d-27a38cd1f467");
        UUID contactPersonId = UUID.fromString("33f7af3d-f624-4624-9745-1f6a02c06a7e");
        UUID contactDetailsId = UUID.fromString("ef15e0ec-5463-437d-ab25-c7cd716046b3");
        clientContactList.get(0).setId(new ClientContactPK(clientId, clientContactId));
        clientContactList.get(0).getContactPerson().setContactPersonId(contactPersonId);
        clientContactList.get(0).getContactPerson().getContactDetails().setContactDetailsId(contactDetailsId);
        clientContactList.get(1).setId(new ClientContactPK(clientId, clientContactId));
        clientContactList.get(1).getContactPerson().setContactPersonId(contactPersonId);
        clientContactList.get(1).getContactPerson().getContactDetails().setContactDetailsId(contactDetailsId);
        //When
       List<ContactPersonDto> result = underTest.mapClientContactListToContactPersonDtoList(clientContactList);
        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isInstanceOf(ContactPersonDto.class);
        assertThat(result.get(0).getFirstName()).isEqualTo(clientContactList.get(0).getContactPerson().getFirstName());
        assertThat(result.get(0).getLastName()).isEqualTo(clientContactList.get(0).getContactPerson().getLastName());
        assertThat(result.get(0).getPositionHeld()).isEqualTo(clientContactList.get(0).getContactPerson().getPositionHeld());
        assertThat(result.get(0).getContactDetailsDto().getPersonalPhone()).isEqualTo(clientContactList.get(0).getContactPerson().getContactDetails().getPersonalPhone());
        assertThat(result.get(0).getContactDetailsDto().getWorkPhone()).isEqualTo(clientContactList.get(0).getContactPerson().getContactDetails().getWorkPhone());
        assertThat(result.get(0).getContactDetailsDto().getEmail()).isEqualTo(clientContactList.get(0).getContactPerson().getContactDetails().getEmail());
        assertThat(result.get(0).getContactDetailsDto().getBusinessEmail()).isEqualTo(clientContactList.get(0).getContactPerson().getContactDetails().getBusinessEmail());
    }

    @Test
    void itShouldMapContactPersonDtoListToClientContactList() {
        //Given
        //When
        List<ClientContact> result = underTest.mapContactPersonDtoListToClientContactList(contactPersonDtoList);
        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isInstanceOf(ClientContact.class);
        assertThat(result.get(0).getContactPerson().getFirstName()).isEqualTo(contactPersonDtoList.get(0).getFirstName());
        assertThat(result.get(0).getContactPerson().getLastName()).isEqualTo(contactPersonDtoList.get(0).getLastName());
        assertThat(result.get(0).getContactPerson().getPositionHeld()).isEqualTo(contactPersonDtoList.get(0).getPositionHeld());
        assertThat(result.get(0).getContactPerson().getContactDetails().getPersonalPhone()).isEqualTo(contactPersonDtoList.get(0).getContactDetailsDto().getPersonalPhone());
        assertThat(result.get(0).getContactPerson().getContactDetails().getWorkPhone()).isEqualTo(contactPersonDtoList.get(0).getContactDetailsDto().getWorkPhone());
        assertThat(result.get(0).getContactPerson().getContactDetails().getEmail()).isEqualTo(contactPersonDtoList.get(0).getContactDetailsDto().getEmail());
        assertThat(result.get(0).getContactPerson().getContactDetails().getBusinessEmail()).isEqualTo(contactPersonDtoList.get(0).getContactDetailsDto().getBusinessEmail());
    }
}