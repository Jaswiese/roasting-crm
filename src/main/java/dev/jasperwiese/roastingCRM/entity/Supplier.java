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
public class Supplier {
    private String supplierId;
    private String companyName;
    private String addressId;
    private String notes;
}
