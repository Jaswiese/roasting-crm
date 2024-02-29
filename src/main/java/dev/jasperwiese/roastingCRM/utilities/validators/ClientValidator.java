package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ClientValidator {

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client validateIfClientExists(String clientId) {

        Optional<Client> clientOptional = clientRepository.findById(UUID.fromString(clientId));

        if (clientOptional.isEmpty()) {
            throw new RuntimeException("Client does not exist");
        }
        return clientOptional.get();
    }

    public Boolean validateIfClientExistsBool(String clientId) {
        return clientRepository.existsById(UUID.fromString(clientId));
    }
}
