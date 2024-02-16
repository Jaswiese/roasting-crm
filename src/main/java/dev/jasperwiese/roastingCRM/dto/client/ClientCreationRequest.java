package dev.jasperwiese.roastingCRM.dto.client;

import dev.jasperwiese.roastingCRM.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientCreationRequest {
    private String clientId;
    private String companyName;
    private String vatNumber;
    private List<AddressDto> addressDtoList;
    private List<ContactPersonDto> clientContactDtoList;
}
