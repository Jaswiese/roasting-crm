package dev.jasperwiese.roastingCRM.utilities.validators;

import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.exceptions.client.ClientNotFoundException;
import dev.jasperwiese.roastingCRM.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientValidatorTest {

    private ClientValidator underTest;
    @Mock
    private ClientRepository clientRepository;
    private UUID clientId;
    @BeforeEach
    void setUp() {
        underTest = new ClientValidator(clientRepository);
        clientId = UUID.fromString("dec60e61-908b-446c-963a-4ac54a9f1ae0");
    }
    @Test
    void itShouldValidateIfClientExists() {
        //Given
        Optional<Client> client = Optional.of(Client.builder()
                .clientId(clientId)
                .build());
        when(clientRepository.findById(clientId)).thenReturn(client);
        //When
        Client result = underTest.validateIfClientExists(clientId.toString());
        //Then
        assertThat(result).isInstanceOf(Client.class);
    }

    @Test
    void itShouldThrowClientNotFoundExceptionIfTheClientDoesNotExist() {
        assertThatThrownBy(() -> underTest.validateIfClientExists(clientId.toString()))
                .isInstanceOf(ClientNotFoundException.class)
                .hasMessageContaining("Client with ID: " + clientId + " does not exist.");
    }

    @Test
    void itShouldValidateIfClientExistsBool() {
        //Given
        when(clientRepository.existsById(clientId)).thenReturn(true);
        //When
        boolean result = underTest.validateIfClientExistsBool(clientId.toString());
        //Then
        assertThat(result).isTrue();
    }
}