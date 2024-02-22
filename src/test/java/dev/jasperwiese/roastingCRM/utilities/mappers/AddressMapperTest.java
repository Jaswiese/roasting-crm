package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressMapperTest {

    private AddressMapper underTest;
    private Address address;
    private AddressDto addressDto;
    private List<Address> addressList;
    private List<AddressDto> addressDtoList;

    @BeforeEach
    void setUp() {
        underTest = new AddressMapper();
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
        addressList = new ArrayList<>();
        addressDtoList = new ArrayList<>();
    }
    @Test
    void itShouldMapToAddressEntityFromUserDto() {
        //Given
        addressDtoList.add(addressDto);
        UserDto userDto = UserDto.builder()
                .addressDtoList(addressDtoList)
                .build();
        //When
        List<Address> entityList = underTest.mapToAddressEntityFromUserDto(userDto);
        //Then
        assertThat(entityList).isNotEmpty();
        assertThat(entityList).hasSize(userDto.getAddressDtoList().size());
        assertThat(entityList.get(0)).isInstanceOf(Address.class);
        assertThat(entityList.get(0).getBuildingUnitNumber()).isEqualTo(userDto.getAddressDtoList().get(0).getBuildingUnitNumber());
        assertThat(entityList.get(0).getStreetAddress()).isEqualTo(userDto.getAddressDtoList().get(0).getStreetAddress());
        assertThat(entityList.get(0).getDistrictSuburb()).isEqualTo(userDto.getAddressDtoList().get(0).getDistrictSuburb());
        assertThat(entityList.get(0).getCity()).isEqualTo(userDto.getAddressDtoList().get(0).getCity());
        assertThat(entityList.get(0).getStateProvince()).isEqualTo(userDto.getAddressDtoList().get(0).getStateProvince());
        assertThat(entityList.get(0).getPostalCode()).isEqualTo(userDto.getAddressDtoList().get(0).getPostalCode());
        assertThat(entityList.get(0).getCountry()).isEqualTo(userDto.getAddressDtoList().get(0).getCountry());
    }

    @Test
    void itShouldMapToAddressDtoListFromAddressList() {
        //Given
        addressList.add(address);
        addressList.add(
                Address.builder()
                        .addressId(UUID.fromString("f98f4cb1-2624-4b80-a5ef-956b7181d780"))
                        .buildingUnitNumber("7")
                        .streetAddress("43 winkel street")
                        .districtSuburb("Durbanville")
                        .city("Cape Town")
                        .stateProvince("Western Cape")
                        .postalCode("7890")
                        .country("South Africa")
                        .build()
        );
        //When
        List<AddressDto> dtoList = underTest.mapToAddressDtoListFromAddressList(addressList);
        //Then
        assertThat(dtoList).isNotEmpty();
        assertThat(dtoList).hasSize(addressList.size());
        assertThat(dtoList.get(0)).isInstanceOf(AddressDto.class);
        assertThat(dtoList.get(0).getBuildingUnitNumber()).isEqualTo(addressList.get(0).getBuildingUnitNumber());
        assertThat(dtoList.get(0).getStreetAddress()).isEqualTo(addressList.get(0).getStreetAddress());
        assertThat(dtoList.get(0).getDistrictSuburb()).isEqualTo(addressList.get(0).getDistrictSuburb());
        assertThat(dtoList.get(0).getCity()).isEqualTo(addressList.get(0).getCity());
        assertThat(dtoList.get(0).getStateProvince()).isEqualTo(addressList.get(0).getStateProvince());
        assertThat(dtoList.get(0).getPostalCode()).isEqualTo(addressList.get(0).getPostalCode());
        assertThat(dtoList.get(0).getCountry()).isEqualTo(addressList.get(0).getCountry());
        assertThat(dtoList.get(1)).isInstanceOf(AddressDto.class);
        assertThat(dtoList.get(1).getBuildingUnitNumber()).isEqualTo(addressList.get(1).getBuildingUnitNumber());
        assertThat(dtoList.get(1).getStreetAddress()).isEqualTo(addressList.get(1).getStreetAddress());
        assertThat(dtoList.get(1).getDistrictSuburb()).isEqualTo(addressList.get(1).getDistrictSuburb());
        assertThat(dtoList.get(1).getCity()).isEqualTo(addressList.get(1).getCity());
        assertThat(dtoList.get(1).getStateProvince()).isEqualTo(addressList.get(1).getStateProvince());
        assertThat(dtoList.get(1).getPostalCode()).isEqualTo(addressList.get(1).getPostalCode());
        assertThat(dtoList.get(1).getCountry()).isEqualTo(addressList.get(1).getCountry());
    }

    @Test
    void itShouldMapToEntity() {
        //Given
        //When
        Address result = underTest.mapToEntity(addressDto);
        //Then
        assertThat(result).isInstanceOf(Address.class);
        assertThat(result.getAddressId()).isNull();
        assertThat(result.getBuildingUnitNumber()).isEqualTo(addressDto.getBuildingUnitNumber());
        assertThat(result.getStreetAddress()).isEqualTo(addressDto.getStreetAddress());
        assertThat(result.getDistrictSuburb()).isEqualTo(addressDto.getDistrictSuburb());
        assertThat(result.getCity()).isEqualTo(addressDto.getCity());
        assertThat(result.getStateProvince()).isEqualTo(addressDto.getStateProvince());
        assertThat(result.getPostalCode()).isEqualTo(addressDto.getPostalCode());
        assertThat(result.getCountry()).isEqualTo(addressDto.getCountry());
    }

    @Test
    void itShouldMapToDto() {
        //Given
        //When
        AddressDto result = underTest.mapToDto(address);
        //Then
        assertThat(result).isInstanceOf(AddressDto.class);
        assertThat(result.getAddressId()).isEqualTo(address.getAddressId().toString());
        assertThat(result.getBuildingUnitNumber()).isEqualTo(address.getBuildingUnitNumber());
        assertThat(result.getStreetAddress()).isEqualTo(address.getStreetAddress());
        assertThat(result.getDistrictSuburb()).isEqualTo(address.getDistrictSuburb());
        assertThat(result.getCity()).isEqualTo(address.getCity());
        assertThat(result.getStateProvince()).isEqualTo(address.getStateProvince());
        assertThat(result.getPostalCode()).isEqualTo(address.getPostalCode());
        assertThat(result.getCountry()).isEqualTo(address.getCountry());
    }
    @Test
    void itShouldAddAddressIdToEntityIfAddressDtoIdIsNotNull() {
        //Given
        addressDto.setAddressId("453009c4-0ef0-4060-be6a-2e30cf62d8a7");
        //When
        Address result = underTest.mapToEntity(addressDto);
        //Then
        assertThat(result).isInstanceOf(Address.class);
        assertThat(result.getAddressId()).isEqualTo(UUID.fromString(addressDto.getAddressId()));
    }
}