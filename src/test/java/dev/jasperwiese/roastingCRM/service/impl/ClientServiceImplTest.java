package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.*;
import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.*;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
import dev.jasperwiese.roastingCRM.entity.client.ClientRoastingProfiles;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientAddressPK;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientContactPK;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientRoastingProfilesPK;
import dev.jasperwiese.roastingCRM.repository.AddressRepository;
import dev.jasperwiese.roastingCRM.repository.ClientRepository;
import dev.jasperwiese.roastingCRM.repository.ContactDetailsRepository;
import dev.jasperwiese.roastingCRM.repository.ContactPersonRepository;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactPersonMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.client.ClientMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.client.ClientRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private ClientServiceImpl underTest;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ContactPersonRepository contactPersonRepository;
    @Mock
    private ContactDetailsRepository contactDetailsRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private ContactPersonMapper contactPersonMapper;
    @Mock
    private ClientRequestMapper clientRequestMapper;
    @Mock
    private ClientMapper clientMapper;
    private List<AddressDto> addressDtoList;
    private List<ContactPersonDto> contactPersonDtoList;
    private ClientCreationRequest clientCreationRequest;
    private Client client;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        //Init ClientServiceImpl class with Mocks for dependencies
        underTest = new ClientServiceImpl(
                clientRepository,
                contactPersonRepository,
                contactDetailsRepository,
                addressRepository,
                addressMapper,
                contactPersonMapper,
                clientRequestMapper,
                clientMapper
        );
        /*
        * Init Entities and Dtos used within the unit tests.
        * namely; valid ClientCreationRequest, Client entity and Client Dto (as well as their nested objects)
        * duplication in data exist, as mappers are used throughout and are tested separately.*/
        //AddressDto List used to create the ClientCreationRequest
        addressDtoList = List.of(
                AddressDto.builder()
                        .buildingUnitNumber("54")
                        .streetAddress("46 Maple Leaf avenue")
                        .city("Cape Town")
                        .districtSuburb("Tafelsig")
                        .stateProvince("Western Cape")
                        .postalCode("7134")
                        .country("South Africa")
                        .build(),
                AddressDto.builder()
                        .buildingUnitNumber("54")
                        .streetAddress("46 Maple Leaf avenue")
                        .city("Cape Town")
                        .districtSuburb("Tafelsig")
                        .stateProvince("Western Cape")
                        .postalCode("7134")
                        .country("South Africa")
                        .build()
        );
        //ContactPersonDtoList used to create the ClientCreationRequest
        contactPersonDtoList = List.of(
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
        //ClientCreationRequest assigned its values.
        clientCreationRequest = ClientCreationRequest.builder()
                .companyName("Coffee")
                .vatNumber("23421122")
                .addressDtoList(addressDtoList)
                .clientContactDtoList(contactPersonDtoList)
                .build();
        //Client Entity and Nested entities init below.
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
        //ClientDto and its nested Dto object init.
        //Creating Lists for the various Dtos used in the clientDto.
        List<AddressDto> addressDtoList = new ArrayList<>();
        //adding addressDtos
        addressDtoList.add(AddressDto.builder()
                .buildingUnitNumber("54")
                .streetAddress("46 Maple Leaf avenue")
                .city("Cape Town")
                .districtSuburb("Tafelsig")
                .stateProvince("Western Cape")
                .postalCode("7134")
                .country("South Africa")
                .build());
        List<ContactPersonDto> contactPersonDtoList = new ArrayList<>();
        //adding contactPerson dtos
        contactPersonDtoList.add(ContactPersonDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .positionHeld("Chief Executive Officer")
                .contactDetailsDto(
                        ContactDetailsDto.builder()
                                .email("beb@gmail.com")
                                .businessEmail("beb@hoof.com")
                                .workPhone("+27654455466")
                                .personalPhone("+27654455543")
                                .build()
                )
                .build()
        );
        List<RoastingProfileDto> roastingProfileDtoList = new ArrayList<>();
        //adding roastProfile dtos
        roastingProfileDtoList.add(RoastingProfileDto.builder()
                .profileName("Light Roast Ugandan Sunshine")
                .roasterModel("Evvecco 500")
                .greenBeansDto(
                        GreenBeansDto.builder()
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
                .targetTemperatureDto(
                        TargetTemperatureDto.builder()
                                .preheat("180")
                                .firstCrack("200")
                                .development("250")
                                .drop("260")
                                .build()
                )
                .timeIntervalsDto(
                        TimeIntervalsDto.builder()
                                .preheat("180")
                                .firstCrack("300")
                                .development("500")
                                .total("650")
                                .build()
                )
                .airFlowSettingsDto(
                        AirFlowSettingsDto.builder()
                                .low("50")
                                .medium("100")
                                .high("200")
                                .build()
                )
                .notes("test")
                .build());
        //ClientDto
        clientDto = ClientDto.builder()
                .companyName("Test Company")
                .vatNumber("23424222")
                .addressDtoList(addressDtoList)
                .contactPersonDtoList(contactPersonDtoList)
                .roastingProfileDtoList(roastingProfileDtoList)
                .build();
    }

    @Test
    void itShouldCreateClient() {
        //Given
        //To reduce additional redundant code, the address entity is simply retrieved from the Client, ClientAddressList.
        given(addressMapper.mapToEntity(any(AddressDto.class)))
                .willReturn(client.getAddresses().get(0).getAddress());
        given(addressRepository.save(any(Address.class)))
                .willReturn(client.getAddresses().get(0).getAddress());

        given(contactPersonMapper.mapToEntity(any(ContactPersonDto.class)))
                .willReturn(client.getClientContacts().get(0).getContactPerson());
        given(contactDetailsRepository.save(any(ContactDetails.class)))
                .willReturn(client.getClientContacts().get(0).getContactPerson().getContactDetails());
        given(contactPersonRepository.save(any(ContactPerson.class)))
                .willReturn(client.getClientContacts().get(0).getContactPerson());

        given(clientRepository.save(any(Client.class)))
                .willReturn(client);
        given(clientRequestMapper.mapClientToCreationRequest(any(Client.class)))
                .willReturn(clientCreationRequest);
        //When
        ClientCreationRequest result = underTest.createClient(clientCreationRequest);
        //Then
        /*Testing that the externally called methods and repositories are called the appropriate amount of times*/
        then(addressMapper).should(times(2)).mapToEntity(any(AddressDto.class));
        then(addressRepository).should(times(2)).save(any(Address.class));
        then(contactPersonMapper).should(times(2)).mapToEntity(any(ContactPersonDto.class));
        then(contactDetailsRepository).should(times(2)).save(any(ContactDetails.class));
        then(contactPersonRepository).should(times(2)).save(any(ContactPerson.class));
        then(clientRepository).should(times(1)).save(any(Client.class));
        then(clientRequestMapper).should(times(1)).mapClientToCreationRequest(any(Client.class));
    }

    @Test
    void itShouldGetAllClients() {
        //Given
        List<Client> clientList = List.of(client);
        List<ClientDto> clientDtoList = List.of(clientDto);
        given(clientRepository.findAll()).willReturn(clientList);
        given(clientMapper.mapToDto(any(Client.class))).willReturn(clientDto);
        //When
        List<ClientDto> result = underTest.getAllClients();
        //Then
        then(clientRepository).should(times(1)).findAll();
        then(clientMapper).should(times(1)).mapToDto(any(Client.class));
        assertThat(result).hasSize(1);
    }

    @Test
    void itShouldFindClientById() {
        //Given
        given(clientRepository.findById(any(UUID.class))).willReturn(Optional.of(client));
        given(clientMapper.mapToDto(any(Client.class))).willReturn(clientDto);
        //When
        ClientDto result = underTest.findClientById(UUID.randomUUID().toString());
        //Then
        then(clientRepository).should(times(1)).findById(any(UUID.class));
        then(clientMapper).should(times(1)).mapToDto(any(Client.class));
        assertThat(result).isInstanceOf(ClientDto.class);
    }

    @Test
    void itShouldThrowRunTimeExceptionIfClientDoesNotExist() {
        //Given
        given(clientRepository.findById(any(UUID.class))).willReturn(Optional.empty());
        //When
        assertThatThrownBy(() -> underTest.findClientById(UUID.randomUUID().toString()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("client not found");

        //Then
        then(clientRepository).shouldHaveNoMoreInteractions();
    }
}