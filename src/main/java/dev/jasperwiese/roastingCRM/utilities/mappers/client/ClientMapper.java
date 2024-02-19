package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.utilities.mappers.roastingMappers.RoastingProfileMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClientMapper {

    private final ClientAddressMapper clientAddressMapper;
    private final ClientContactMapper clientContactMapper;
    private final RoastingProfileMapper roastingProfileMapper;
    private final ClientRoastingProfileMapper clientRoastingProfileMapper;
    public ClientMapper(ClientAddressMapper clientAddressMapper,
                        ClientContactMapper clientContactMapper,
                        RoastingProfileMapper roastingProfileMapper,
                        ClientRoastingProfileMapper clientRoastingProfileMapper) {
        this.clientAddressMapper = clientAddressMapper;
        this.clientContactMapper = clientContactMapper;
        this.roastingProfileMapper = roastingProfileMapper;
        this.clientRoastingProfileMapper = clientRoastingProfileMapper;
    }

    public Client mapToEntity(ClientDto clientDto) {
        Client client = new Client();
        if(clientDto.getClientId() != null && !clientDto.getClientId().isEmpty()) {
            client.setClientId(UUID.fromString(clientDto.getClientId()));
        }

        client.setCompanyName(clientDto.getCompanyName());
        client.setVatNumber(clientDto.getVatNumber());

        if(clientDto.getAddressDtoList() != null && !clientDto.getAddressDtoList().isEmpty()) {
            client.setAddresses(clientAddressMapper.mapAddressDtoListToClientAddressList(clientDto.getAddressDtoList()));
        }

        if (clientDto.getContactPersonDtoList() != null && !clientDto.getContactPersonDtoList().isEmpty()){
            client.setClientContacts(clientContactMapper.mapContactPersonDtoListToClientContactList(clientDto.getContactPersonDtoList()));
        }

        if(clientDto.getRoastingProfileDtoList() != null && !clientDto.getRoastingProfileDtoList().isEmpty()) {
            client.setClientRoastingProfiles(clientRoastingProfileMapper.mapRoastingProfileDtoListToClientRoastingProfilesList(clientDto.getRoastingProfileDtoList()));
        }

        return client;
    }

    public ClientDto mapToDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(String.valueOf(client.getClientId()));
        clientDto.setCompanyName(client.getCompanyName());
        clientDto.setVatNumber(client.getVatNumber());

        if(client.getAddresses() != null && !client.getAddresses().isEmpty()) {
            clientDto.setAddressDtoList(clientAddressMapper.mapClientAddressListToAddressDtoList(client.getAddresses()));
        }

        if(client.getClientContacts() != null && !client.getClientContacts().isEmpty()) {
            clientDto.setContactPersonDtoList(clientContactMapper.mapClientContactListToContactPersonDtoList(client.getClientContacts()));
        }

        if(client.getClientRoastingProfiles() != null && !client.getClientRoastingProfiles().isEmpty()) {
            clientDto.setRoastingProfileDtoList(clientRoastingProfileMapper.mapClientRoastingProfilesListToRoastingProfileDtoList(client.getClientRoastingProfiles()));
        }

        return clientDto;
    }
}
