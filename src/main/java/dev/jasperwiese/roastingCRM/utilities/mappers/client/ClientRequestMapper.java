package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientRequestMapper {

    private final ClientAddressMapper clientAddressMapper;
    private final ClientContactMapper clientContactMapper;

    public ClientRequestMapper(ClientAddressMapper clientAddressMapper, ClientContactMapper clientContactMapper) {
        this.clientAddressMapper = clientAddressMapper;
        this.clientContactMapper = clientContactMapper;
    }

    public ClientCreationRequest mapClientToCreationRequest(Client client){
        return ClientCreationRequest.builder()
                .clientId(String.valueOf(client.getClientId()))
                .companyName(client.getCompanyName())
                .vatNumber(client.getVatNumber())
                .addressDtoList(clientAddressMapper.mapClientAddressListToAddressDtoList(client.getAddresses()))
                .clientContactDtoList(clientContactMapper.mapClientContactListToContactPersonDtoList(client.getClientContacts()))
                .build();
    }
}
