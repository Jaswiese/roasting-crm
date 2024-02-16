package dev.jasperwiese.roastingCRM.service;

import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;

public interface ClientService {

   ClientCreationRequest createClient(ClientCreationRequest clientCreationDto);


}
