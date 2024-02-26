package dev.jasperwiese.roastingCRM.utilities.mappers.user;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import dev.jasperwiese.roastingCRM.dto.EmergencyContactDto;
import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.EmergencyContact;
import dev.jasperwiese.roastingCRM.entity.user.User;
import dev.jasperwiese.roastingCRM.entity.user.UserAddress;
import dev.jasperwiese.roastingCRM.entity.user.UserAddressPK;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactDetailsMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.EmergencyContactMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper underTest;
    private UserAddressMapper userAddressMapper;
    private ContactDetailsMapper contactDetailsMapper;
    private EmergencyContactMapper emergencyContactMapper;
    private AddressMapper addressMapper;
    private Address address;
    private AddressDto addressDto;
    private ContactDetails contactDetails;
    private ContactDetailsDto contactDetailsDto;
    private EmergencyContact emergencyContact;
    private EmergencyContactDto emergencyContactDto;
    private UserAddress userAddress;
    private List<UserAddress> userAddressList;
    private List<AddressDto> addressDtoList;
    private User user;
    private UserDto userDto;


    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapper();
        userAddressMapper = new UserAddressMapper(addressMapper);
        contactDetailsMapper = new ContactDetailsMapper();
        emergencyContactMapper = new EmergencyContactMapper();
        underTest = new UserMapper(userAddressMapper, contactDetailsMapper, emergencyContactMapper, addressMapper);
        //address
        UUID addressId = UUID.fromString("453009c4-0ef0-4060-be6a-2e30cf62d8a7");
        address = Address.builder()
                .addressId(addressId)
                .buildingUnitNumber("54")
                .streetAddress("46 Maple Leaf avenue")
                .city("Cape Town")
                .districtSuburb("Tafelsig")
                .stateProvince("Western Cape")
                .postalCode("7134")
                .country("South Africa")
                .build();
        addressDto = AddressDto.builder()
                .buildingUnitNumber("54")
                .streetAddress("46 Maple Leaf avenue")
                .city("Cape Town")
                .districtSuburb("Tafelsig")
                .stateProvince("Western Cape")
                .postalCode("7134")
                .country("South Africa")
                .build();
        //contactDetails
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
        //EmergencyContact
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
        UUID userId = UUID.fromString("368595d9-48b7-4946-b2de-40dd906efb3e");
        user = User.builder()
                .userId(userId)
                .verified(true)
                .firstName("Jeff")
                .lastName("mynameis")
                .contactDetails(contactDetails)
                .password("password")
                .position("Roasting Technician")
                .role("admin")
                .emergencyContact(emergencyContact)
                .build();

        UserAddress userAddress = UserAddress.builder()
                .id(new UserAddressPK(userId, addressId))
                .user(user)
                .address(address)
                .primaryAddress(true)
                .build();
        userAddressList = new ArrayList<>();
        userAddressList.add(userAddress);
        user.setAddresses(userAddressList);

        addressDtoList = new ArrayList<>();
        addressDtoList.add(addressDto);
        userDto = UserDto.builder()
                .verified(true)
                .firstName("Jeff")
                .lastName("mynameis")
                .password("password")
                .position("Roasting Technician")
                .role("admin")
                .contactDetailsDto(contactDetailsDto)
                .emergencyContactDto(emergencyContactDto)
                .addressDtoList(addressDtoList)
                .build();

    }

    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        User result = underTest.mapToEntity(userDto);
        //Then
        assertThat(result).isInstanceOf(User.class);
        assertThat(result.getUserId()).isNull();
        assertThat(result.getVerified()).isEqualTo(true);
        assertThat(result.getFirstName()).isEqualTo("Jeff");
        assertThat(result.getLastName()).isEqualTo("mynameis");
        assertThat(result.getContactDetails()).isInstanceOf(ContactDetails.class);
        assertThat(result.getPassword()).isEqualTo("password");
        assertThat(result.getPosition()).isEqualTo("Roasting Technician");
        assertThat(result.getRole()).isEqualTo("admin");
        assertThat(result.getEmergencyContact()).isInstanceOf(EmergencyContact.class);
        //assertThat(result.getAddresses().)).isInstanceOf(UserAddress.class);
    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        UserDto result = underTest.mapToDto(user);
        //Then
        assertThat(result).isInstanceOf(UserDto.class);
        assertThat(result.getUserId()).isEqualTo(user.getUserId().toString());
        assertThat(result.getVerified()).isEqualTo(user.getVerified());
        assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(result.getLastName()).isEqualTo(user.getLastName());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
        assertThat(result.getPosition()).isEqualTo(user.getPosition());
        assertThat(result.getRole()).isEqualTo(user.getRole());
        assertThat(result.getContactDetailsDto()).isInstanceOf(ContactDetailsDto.class);
        assertThat(result.getEmergencyContactDto()).isInstanceOf(EmergencyContactDto.class);
        assertThat(result.getAddressDtoList().get(0)).isInstanceOf(AddressDto.class);
    }

    @Test
    void itShouldAssignUserIdToUserEntityIfUserDtoIdIsNotNullOrEmpty(){
        //Given
        userDto.setUserId("368595d9-48b7-4946-b2de-40dd906efb3e");
        //When
        User result = underTest.mapToEntity(userDto);
        //then
        assertThat(result.getUserId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo(UUID.fromString("368595d9-48b7-4946-b2de-40dd906efb3e"));
    }
}