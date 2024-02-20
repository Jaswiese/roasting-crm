package dev.jasperwiese.roastingCRM.controller.client;

import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.service.impl.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("client/all")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("client/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable String clientId) {
        return new ResponseEntity<>(clientService.findClientById(clientId), HttpStatus.OK);
    }

}
