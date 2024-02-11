package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AddressMapper {

    public List<Address> mapToAddressEntityFromUserDto(UserDto userDto) {
        List<AddressDto> addressesDto = userDto.getAddressDtoList();
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < addressesDto.size(); i++) {
            AddressDto addressDto = addressesDto.get(i);
            Address address = mapToEntity(addressDto);
            addresses.add(address);
        }
        return addresses;

    }

    public List<AddressDto> mapToAddressDtoListFromAddressList(List<Address> addressList) {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (int i = 0; i < addressList.size(); i++) {
            Address address = addressList.get(i);
            AddressDto addressDto = AddressDto.builder()
                    .addressId(address.getAddressId().toString())
                    .buildingUnitNumber(address.getBuildingUnitNumber())
                    .streetAddress(address.getStreetAddress())
                    .districtSuburb(address.getDistrictSuburb())
                    .city(address.getCity())
                    .stateProvince(address.getStateProvince())
                    .postalCode(address.getPostalCode())
                    .country(address.getCountry())
                    .build();
            addressDtoList.add(addressDto);
        }
        return addressDtoList;
    }

    public Address mapToEntity(AddressDto addressDto) {
        //rewrite without builder pattern due to null pointer errors in transit
        //
        Address address = new Address();
        if(addressDto.getAddressId() != null && !addressDto.getAddressId().isEmpty()) {
            address.setAddressId(UUID.fromString(addressDto.getAddressId()));
        }
        address.setBuildingUnitNumber(addressDto.getBuildingUnitNumber());
        address.setStreetAddress(addressDto.getStreetAddress());
        address.setDistrictSuburb(addressDto.getDistrictSuburb());
        address.setCity(addressDto.getCity());
        address.setStateProvince(addressDto.getStateProvince());
        address.setPostalCode(addressDto.getPostalCode());
        address.setCountry(addressDto.getCountry());
        return address;
    }

    public AddressDto mapToDto(Address address) {
        return AddressDto.builder()
                .addressId(String.valueOf(address.getAddressId()))
                .buildingUnitNumber(address.getBuildingUnitNumber())
                .streetAddress(address.getStreetAddress())
                .districtSuburb(address.getDistrictSuburb())
                .city(address.getCity())
                .stateProvince(address.getStateProvince())
                .country(address.getCountry())
                .build();
    }

}
