package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientMapper {

    private ClientAddressMapper clientAddressMapper;
    private ClientContactMapper clientContactMapper;
    private RoastingProfileMapper roastingProfileMapper;
    public ClientMapper(ClientAddressMapper clientAddressMapper,
                        ClientContactMapper clientContactMapper,
                        RoastingProfileMapper roastingProfileMapper) {
        this.clientAddressMapper = clientAddressMapper;
        this.clientContactMapper = clientContactMapper;
        this.roastingProfileMapper = roastingProfileMapper;
    }

    public Client mapToEntity(ClientDto clientDto) {
        Client client = new Client();
        if(clientDto.getClientId() != null && !clientDto.getClientId().isEmpty()) {
            client.setClientId(UUID.fromString(clientDto.getClientId()));
        }
        client.setCompanyName(clientDto.getCompanyName());
        client.setVatNumber(clientDto.getVatNumber());
        client.setAddresses(clientAddressMapper.mapAddressDtoListToClientAddressList(clientDto.getAddressDtoList()));
        client.setClientContacts(clientContactMapper.mapContactPersonDtoListToClientContactList(clientDto.getContactPersonDtoList()));
        //client.setClientRoastingProfiles(clientDto.getRoastingProfileDtoList());
        return client;
    }

    public ClientDto mapToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(String.valueOf(client.getClientId()));
        clientDto.setCompanyName(client.getCompanyName());
        clientDto.setVatNumber(client.getVatNumber());
        clientDto.setAddressDtoList(clientAddressMapper.mapClientAddressListToAddressDtoList(client.getAddresses()));
        clientDto.setContactPersonDtoList(clientContactMapper.mapClientContactListToContactPersonDtoList(client.getClientContacts()));
        //clientDto.setRoastingProfileDtoList(client.getClientRoastingProfiles());
        return clientDto;
    }
}
