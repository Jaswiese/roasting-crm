package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
// TODO: Add jpa annotations
@Table
public class SupplierContacts {

    private String supplierId;

    private String contactPersonId;

    private String preferredContact;

    private String notes;
}
