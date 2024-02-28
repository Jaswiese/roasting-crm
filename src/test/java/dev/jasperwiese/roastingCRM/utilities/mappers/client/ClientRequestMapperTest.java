package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientAddressPK;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientContactPK;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactDetailsMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactPersonMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientRequestMapperTest {

    private ClientRequestMapper underTest;
    private ClientCreationRequest clientCreationRequest;
    private Client client;

    @BeforeEach
    void setUp() {
        //ClientRequestMapper dependencies init.
        //ClientAddressMapper & dependency addressMapper init.
        AddressMapper addressMapper = new AddressMapper();
        ClientAddressMapper clientAddressMapper = new ClientAddressMapper(addressMapper);
        //ClientContactMapper & dependencies init.
        ContactDetailsMapper contactDetailsMapper = new ContactDetailsMapper();
        ContactPersonMapper contactPersonMapper = new ContactPersonMapper(contactDetailsMapper);
        ClientContactMapper clientContactMapper = new ClientContactMapper(contactPersonMapper);
        //ClientRequestMapper with dependencies init.
        underTest = new ClientRequestMapper(clientAddressMapper, clientContactMapper);

        //IDs for the various nested entities within the Client entity init.
        UUID clientId = UUID.fromString("1fc870c5-9a02-48a8-8a0f-c29c9df7a3b2");
        UUID addressId = UUID.fromString("f95452df-c70d-4f2f-95c8-340942367c23");
        //ClientAddressId created - as it represents a linked table. (note this is the same for ClientContact & ClientRoastingProfiles).
        ClientAddressPK clientAddressPK = new ClientAddressPK(clientId, addressId);

        //ClientAddress List init for Client entity.
        List<ClientAddress> clientAddressList = new ArrayList<>();
        clientAddressList.add(
                ClientAddress.builder()
                        .id(clientAddressPK)
                        .primaryAddress(true)
                        .address(
                                Address.builder()
                                        .addressId(addressId)
                                        .buildingUnitNumber("54")
                                        .streetAddress("46 Maple Leaf avenue")
                                        .city("Cape Town")
                                        .districtSuburb("Tafelsig")
                                        .stateProvince("Western Cape")
                                        .postalCode("7134")
                                        .country("South Africa")
                                        .build()
                        )
                        .name("test")
                        .build()
        );

        //ID for the ClientContact entity and it's nested entities.
        UUID contactPersonId = UUID.fromString("23900049-cf6c-4a35-970b-94d14362d774");
        UUID contactDetailsId= UUID.fromString("09d18292-3c1e-45b6-947d-9dea8e1500c1");
        ClientContactPK clientContactPK = new ClientContactPK(clientId, contactPersonId);

        List<ClientContact> clientContactList = new ArrayList<>();
        clientContactList.add(
                ClientContact.builder()
                        .id(clientContactPK)
                        .preferredContact(true)
                        .contactPerson(
                                ContactPerson.builder()
                                        .contactPersonId(contactPersonId)
                                        .firstName("Jane")
                                        .lastName("Doe")
                                        .positionHeld("Chief Executive Officer")
                                        .contactDetails(
                                                ContactDetails.builder()
                                                        .contactDetailsId(contactDetailsId)
                                                        .email("beb@gmail.com")
                                                        .businessEmail("beb@hoof.com")
                                                        .workPhone("+27654455466")
                                                        .personalPhone("+27654455543")
                                                        .build()
                                        )
                                        .build()
                        )
                        .notes("friendly, has two dogs and a cat named Hodor")
                        .build()
        );
        //IDs for the ClientRoastingProfiles entity and its nested entities.
        UUID roastingProfileId = UUID.fromString("d096b308-ef39-47b4-ac53-a3f294c68a2e");
        ClientRoastingProfilesPK clientRoastingProfilesPK = new ClientRoastingProfilesPK(roastingProfileId, clientId);
        UUID greenBeansId = UUID.fromString("40069172-bfd4-4a22-8a60-363e8ecc1e52");
        UUID targetTemperatureId = UUID.fromString("875dd5ef-5eb0-4ef1-85d0-22158c16cf25");
        UUID timeIntervalsId = UUID.fromString("de63a855-48b8-4114-9993-5ca5fe327179");
        UUID airflowSettingsId = UUID.fromString("133a22ac-3802-4e12-95a4-fdf8296281b4");

        //ClientRoastingProfilesList init used in Client entity.
        List<ClientRoastingProfiles> clientRoastingProfilesList = new ArrayList<>();
        clientRoastingProfilesList.add(
                ClientRoastingProfiles.builder()
                        .id(clientRoastingProfilesPK)
                        .roastingProfile(
                                RoastingProfile.builder()
                                        .roastingProfileId(roastingProfileId)
                                        .profileName("Light Roast Ugandan Sunshine")
                                        .roasterModel("Evvecco 500")
                                        .greenBeans(
                                                GreenBeans.builder()
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
                                                        .build()
                                        )
                                        .targetTemperature(
                                                TargetTemperature.builder()
                                                        .targetTemperatureId(targetTemperatureId)
                                                        .preheat("180")
                                                        .firstCrack("200")
                                                        .development("250")
                                                        .dropTemperature("260")
                                                        .build()
                                        )
                                        .timeIntervals(
                                                TimeIntervals.builder()
                                                        .timeIntervalsId(timeIntervalsId)
                                                        .preheat("180")
                                                        .firstCrack("300")
                                                        .development("500")
                                                        .total("650")
                                                        .build()
                                        )
                                        .airflowSettings(
                                                AirflowSettings.builder()
                                                        .airflowSettingsId(airflowSettingsId)
                                                        .low("50")
                                                        .medium("100")
                                                        .high("200")
                                                        .build()
                                        )
                                        .notes("test")
                                        .build()
                        )
                        .build()
        );
        client = Client.builder()
                .clientId(clientId)
                .companyName("Test Company")
                .vatNumber("23424222")
                .addresses(clientAddressList)
                .clientContacts(clientContactList)
                .clientRoastingProfiles(clientRoastingProfilesList)
                .build();
    }

    @Test
    void itShouldMapClientToCreationRequest() {
        //Given
        //When
        ClientCreationRequest result = underTest.mapClientToCreationRequest(client);
        //Then
        assertThat(result).isInstanceOf(ClientCreationRequest.class);
        assertThat(result.getCompanyName()).isEqualTo(client.getCompanyName());
        assertThat(result.getVatNumber()).isEqualTo(client.getVatNumber());
        assertThat(result.getAddressDtoList()).hasSameSizeAs(client.getAddresses());
        assertThat(result.getClientContactDtoList()).hasSameSizeAs(client.getClientContacts());
    }
}