package dev.jasperwiese.roastingCRM.service;

import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.dto.client.ClientDto;

import java.util.List;

public interface ClientService {

   ClientCreationRequest createClient(ClientCreationRequest clientCreationDto);

   List<ClientDto> getAllClients();

   ClientDto findClientById(String clientId);

}
