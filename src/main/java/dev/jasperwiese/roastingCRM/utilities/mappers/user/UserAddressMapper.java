package dev.jasperwiese.roastingCRM.utilities.mappers.user;

import dev.jasperwiese.roastingCRM.dto.UserDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.user.UserAddress;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAddressMapper {

    private final AddressMapper addressMapper;

    public UserAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<Address> mapUserAddressListToAddressList(List<UserAddress> addresses) {
        List<Address> addressList = new ArrayList<>();
        for (UserAddress userAddress : addresses) {
            addressList.add(userAddress.getAddress());
        }
        return addressList;
    }
}
