package dev.jasperwiese.roastingCRM.dto.client;

import dev.jasperwiese.roastingCRM.dto.ContactDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactPersonDto {

    private String contactPersonId;

    private String firstName;

    private String lastName;

    private String positionHeld;

    private ContactDetailsDto contactDetailsDto;
}
