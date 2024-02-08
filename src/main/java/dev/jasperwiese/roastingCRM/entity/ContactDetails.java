package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class ContactDetails {

    private String contactDetailsId;

    private String email;

    private String businessEmail;

    private String workPhone;

    private String personalPhone;
}
