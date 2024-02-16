package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientAddressMapper {

    private AddressMapper addressMapper;

    public ClientAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<AddressDto> mapClientAddressListToAddressDtoList(List<ClientAddress> clientAddressList) {
        List<AddressDto> addressDtoList = new ArrayList<>();
        for (ClientAddress clientAddress : clientAddressList) {
            addressDtoList.add(addressMapper.mapToDto(clientAddress.getAddress()));
        }
        return addressDtoList;
    }
}
