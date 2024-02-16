package dev.jasperwiese.roastingCRM.controller.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.service.impl.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ClientControllerV1 {

    private final ClientServiceImpl clientService;

    public ClientControllerV1(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("client/create")
    public ResponseEntity<ClientCreationRequest> createClient(@RequestBody ClientCreationRequest clientCreationRequest) {
        return new ResponseEntity<>(clientService.createClient(clientCreationRequest), HttpStatus.OK);
    }
}
