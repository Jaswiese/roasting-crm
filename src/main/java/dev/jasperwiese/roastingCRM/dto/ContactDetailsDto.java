package dev.jasperwiese.roastingCRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetailsDto {
    private String contactDetailsId;
    private String email;
    private String businessEmail;
    private String workPhone;
    private String personalPhone;
}
