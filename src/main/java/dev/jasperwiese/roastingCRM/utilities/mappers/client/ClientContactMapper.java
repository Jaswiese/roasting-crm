package dev.jasperwiese.roastingCRM.utilities.mappers.client;

import dev.jasperwiese.roastingCRM.dto.client.ContactPersonDto;
import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
import dev.jasperwiese.roastingCRM.utilities.mappers.ContactPersonMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientContactMapper {

    private ContactPersonMapper contactPersonMapper;

    public ClientContactMapper(ContactPersonMapper contactPersonMapper) {
        this.contactPersonMapper = contactPersonMapper;
    }

    public List<ContactPersonDto> mapClientContactListToContactPersonDtoList(List<ClientContact> clientContactList) {
        List<ContactPersonDto> contactPersonDtoList = new ArrayList<>();
        for (int i = 0; i < clientContactList.size(); i++) {
            ContactPersonDto contactPersonDto = contactPersonMapper.mapToDto(clientContactList.get(i).getContactPerson());
            contactPersonDtoList.add(contactPersonDto);
        }
        return contactPersonDtoList;
    }
}
