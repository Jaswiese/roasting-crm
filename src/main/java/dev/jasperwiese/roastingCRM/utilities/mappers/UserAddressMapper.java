package dev.jasperwiese.roastingCRM.utilities.mappers;

import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.user.UserAddress;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAddressMapper {

    private final AddressMapper addressMapper;

    public UserAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<UserAddress> mapUserDtoToUserAddressList (UserDto userDto) {
        List<Address> addresses = addressMapper.mapToAddressEntityFromUserDto(userDto);
        List<UserAddress> userAddresses = new ArrayList<>();
        for (int i = 0; i < addresses.size(); i++) {
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(addresses.get(i));
            userAddresses.add(userAddress);
        }
        return userAddresses;
    }

    public List<Address> mapUserAddressListToAddressList(List<UserAddress> addresses) {
        List<Address> addressList = new ArrayList<>();
        for (UserAddress userAddress : addresses) {
            addressList.add(userAddress.getAddress());
        }
        return addressList;
    }
}
