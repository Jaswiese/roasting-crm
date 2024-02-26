package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.entity.user.UserAddress;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientAddressMapperTest {

    private ClientAddressMapper underTest;
    private List<AddressDto> addressDtoList;
    private List<ClientAddress> clientAddressList;

    @BeforeEach
    void setUp() {
        AddressMapper addressMapper = new AddressMapper();
        underTest = new ClientAddressMapper(addressMapper);
        addressDtoList = Arrays.asList(
                AddressDto.builder()
                        .buildingUnitNumber("45")
                        .streetAddress("14 Freeman Road ")
                        .districtSuburb("Tafelsig")
                        .city("Cape Town")
                        .stateProvince("Western Cape")
                        .postalCode("7220")
                        .country("South Africa")
                        .build(),
                AddressDto.builder()
                        .buildingUnitNumber("10")
                        .streetAddress("30 Grondmond Avenue")
                        .districtSuburb("NorthCliff")
                        .city("Johannesburg")
                        .stateProvince("Gauteng")
                        .postalCode("2195")
                        .country("South Africa")
                        .build()
        );
        clientAddressList = Arrays.asList(
                ClientAddress.builder()
                        .address(Address.builder()
                                .buildingUnitNumber("45")
                                .streetAddress("14 Freeman Road ")
                                .districtSuburb("Tafelsig")
                                .city("Cape Town")
                                .stateProvince("Western Cape")
                                .postalCode("7220")
                                .country("South Africa")
                                .build())
                        .build(),
                ClientAddress.builder()
                        .address(Address.builder()
                                .buildingUnitNumber("10")
                                .streetAddress("30 Grondmond Avenue")
                                .districtSuburb("NorthCliff")
                                .city("Johannesburg")
                                .stateProvince("Gauteng")
                                .postalCode("2195")
                                .country("South Africa")
                                .build())
                        .build()
        );
    }

    @Test
    void itShouldMapClientAddressListToAddressDtoList() {
        //Given
        //When
        List<AddressDto> result = underTest.mapClientAddressListToAddressDtoList(clientAddressList);
        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isInstanceOf(AddressDto.class);
        assertThat(result.get(0).getBuildingUnitNumber()).isEqualTo(clientAddressList.get(0).getAddress().getBuildingUnitNumber());
        assertThat(result.get(0).getStreetAddress()).isEqualTo(clientAddressList.get(0).getAddress().getStreetAddress());
        assertThat(result.get(0).getDistrictSuburb()).isEqualTo(clientAddressList.get(0).getAddress().getDistrictSuburb());
        assertThat(result.get(0).getCity()).isEqualTo(clientAddressList.get(0).getAddress().getCity());
        assertThat(result.get(0).getStateProvince()).isEqualTo(clientAddressList.get(0).getAddress().getStateProvince());
        assertThat(result.get(0).getPostalCode()).isEqualTo(clientAddressList.get(0).getAddress().getPostalCode());
        assertThat(result.get(0).getCountry()).isEqualTo(clientAddressList.get(0).getAddress().getCountry());
        assertThat(result.get(1)).isInstanceOf(AddressDto.class);
    }

    @Test
    void itShouldMapAddressDtoListToClientAddressList() {
        //Given
        //When
        List<ClientAddress> result = underTest.mapAddressDtoListToClientAddressList(addressDtoList);
        //Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getAddress()).isInstanceOf(Address.class);
        assertThat(result.get(0).getAddress().getBuildingUnitNumber()).isEqualTo(addressDtoList.get(0).getBuildingUnitNumber());
        assertThat(result.get(0).getAddress().getStreetAddress()).isEqualTo(addressDtoList.get(0).getStreetAddress());
        assertThat(result.get(0).getAddress().getDistrictSuburb()).isEqualTo(addressDtoList.get(0).getDistrictSuburb());
        assertThat(result.get(0).getAddress().getCity()).isEqualTo(addressDtoList.get(0).getCity());
        assertThat(result.get(0).getAddress().getStateProvince()).isEqualTo(addressDtoList.get(0).getStateProvince());
        assertThat(result.get(0).getAddress().getPostalCode()).isEqualTo(addressDtoList.get(0).getPostalCode());
        assertThat(result.get(0).getAddress().getCountry()).isEqualTo(addressDtoList.get(0).getCountry());
        assertThat(result.get(0).getAddress()).isInstanceOf(Address.class);
    }
}