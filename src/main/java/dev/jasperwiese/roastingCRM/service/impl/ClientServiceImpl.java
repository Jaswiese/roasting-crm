package dev.jasperwiese.roastingCRM.service.impl;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import dev.jasperwiese.roastingCRM.dto.client.ClientCreationRequest;
import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.Address;
import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import dev.jasperwiese.roastingCRM.entity.client.Client;
import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientAddressPK;
import dev.jasperwiese.roastingCRM.entity.client.pk.ClientContactPK;
import dev.jasperwiese.roastingCRM.repository.*;
import dev.jasperwiese.roastingCRM.service.ClientService;
import dev.jasperwiese.roastingCRM.utilities.mappers.AddressMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactPersonMapper;
import dev.jasperwiese.roastingCRM.utilities.mappers.client.ClientRequestMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ContactPersonRepository contactPersonRepository;
    private ContactDetailsRepository contactDetailsRepository;
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;
    private ContactPersonMapper contactPersonMapper;
    private ClientRequestMapper clientRequestMapper;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ContactPersonRepository contactPersonRepository,
                             ContactDetailsRepository contactDetailsRepository,
                             AddressRepository addressRepository,
                             AddressMapper addressMapper,
                             ContactPersonMapper contactPersonMapper,
                             ClientRequestMapper clientRequestMapper) {
        this.clientRepository = clientRepository;
        this.contactPersonRepository = contactPersonRepository;
        this.contactDetailsRepository = contactDetailsRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.contactPersonMapper = contactPersonMapper;
        this.clientRequestMapper = clientRequestMapper;
    }

    @Override
    public ClientCreationRequest createClient(ClientCreationRequest clientCreationRequest) {

        Client client = new Client();
        //generate Ids
        client.setClientId(UUID.randomUUID());
        client.setCompanyName(clientCreationRequest.getCompanyName());
        client.setVatNumber(clientCreationRequest.getVatNumber());

        List<Address> addressList = new ArrayList<>();
        List<AddressDto> addressDtoList = clientCreationRequest.getAddressDtoList();
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

        List<ContactPersonDto> contactPersonDtoList = clientCreationRequest.getClientContactDtoList();
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

        client = clientRepository.save(client);

        return clientRequestMapper.mapClientToCreationRequest(client);
    }
}
