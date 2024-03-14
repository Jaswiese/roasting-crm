package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.dto.client.ClientDto;
import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientAddressPK;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientContactPK;
import dev.jasperwiese.roastingCRM.exceptions.client.ClientNotFoundException;
import dev.jasperwiese.roastingCRM.repository.*;
import dev.jasperwiese.roastingCRM.service.ClientService;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactPersonMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.client.ClientMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.client.ClientRequestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ContactPersonRepository contactPersonRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final ContactPersonMapper contactPersonMapper;
    private final ClientRequestMapper clientRequestMapper;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ContactPersonRepository contactPersonRepository,
                             ContactDetailsRepository contactDetailsRepository,
                             AddressRepository addressRepository,
                             AddressMapper addressMapper,
                             ContactPersonMapper contactPersonMapper,
                             ClientRequestMapper clientRequestMapper,
                             ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.contactPersonRepository = contactPersonRepository;
        this.contactDetailsRepository = contactDetailsRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.contactPersonMapper = contactPersonMapper;
        this.clientRequestMapper = clientRequestMapper;
        this.clientMapper = clientMapper;
    }
    @Transactional
    @Override
    public ClientCreationRequest createClient(ClientCreationRequest clientCreationRequest) {

        Client client = new Client();
        //generate Ids
        client.setClientId(UUID.randomUUID());
        client.setCompanyName(clientCreationRequest.getCompanyName());
        client.setVatNumber(clientCreationRequest.getVatNumber());

        List<Address> addressList = new ArrayList<>();
        List<AddressDto> addressDtoList = clientCreationRequest.getAddressDtoList();

        if(addressDtoList != null && !addressDtoList.isEmpty()) {
            //AddressDto -> ClientAddress
            for (int i = 0; i < addressDtoList.size() ; i++) {

                Address address = addressMapper.mapToEntity(addressDtoList.get(i));

                address.setAddressId(UUID.randomUUID());

                address = addressRepository.save(address);

                ClientAddress clientAddress = new ClientAddress();
                clientAddress.setId(new ClientAddressPK(client.getClientId(), address.getAddressId()));
                clientAddress.setClient(client);
                clientAddress.setAddress(address);
                client.getAddresses().add(clientAddress);
            }
        }

        List<ContactPersonDto> contactPersonDtoList = clientCreationRequest.getClientContactDtoList();

        if(contactPersonDtoList != null && !contactPersonDtoList.isEmpty()) {
            //ContactPersonDto -> ClientContacts
            for (int i = 0; i < contactPersonDtoList.size(); i++) {

                ContactPerson contactPerson = contactPersonMapper.mapToEntity(contactPersonDtoList.get(i));
                //Set Ids
                contactPerson.setContactPersonId(UUID.randomUUID());
                ContactDetails contactDetails = contactPerson.getContactDetails();
                contactDetails.setContactDetailsId(UUID.randomUUID());

                contactDetails = contactDetailsRepository.save(contactDetails);

                contactPerson.setContactDetails(contactDetails);

                contactPerson = contactPersonRepository.save(contactPerson);

                ClientContact clientContact = new ClientContact();
                clientContact.setId(new ClientContactPK(client.getClientId(), contactPerson.getContactPersonId()));
                clientContact.setClient(client);
                clientContact.setContactPerson(contactPerson);

                client.getClientContacts().add(clientContact);
            }
        }

            client = clientRepository.save(client);

            return clientRequestMapper.mapClientToCreationRequest(client);

    }
    @Transactional
    public List<ClientDto> getAllClients(){
        List<Client> clientList = clientRepository.findAll();
        List<ClientDto> clientDtoList = new ArrayList<>();
        for (int i = 0; i < clientList.size(); i++) {
            ClientDto clientDto = clientMapper.mapToDto(clientList.get(i));
            clientDtoList.add(clientDto);
        }
        return clientDtoList;
    }
    @Transactional
    @Override
    public ClientDto findClientById(String clientId) {
        ClientDto clientDto = new ClientDto();
        Optional<Client> client = clientRepository.findById(UUID.fromString(clientId));
        if (client.isPresent()) {
            return  clientDto = clientMapper.mapToDto(client.get());
        }
        throw new ClientNotFoundException("Client with ID: " + clientId + " was not found.");
    }

}
