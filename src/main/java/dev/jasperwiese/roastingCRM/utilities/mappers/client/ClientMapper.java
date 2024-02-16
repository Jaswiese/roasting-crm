package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    //add extra mappers

    public Client mapToEntity(ClientDto clientDto) {
        Client client = new Client();

        return client;
    }

    public ClientDto mapToDto(Client client) {
        ClientDto clientDto = new ClientDto();

        return clientDto;
    }
}
