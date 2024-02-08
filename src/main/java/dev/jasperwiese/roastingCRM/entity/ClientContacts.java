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
public class ClientContacts {
    private String clientId;

    private String contactPersonId;

    private Boolean preferredContact;

    private String notes;
}
