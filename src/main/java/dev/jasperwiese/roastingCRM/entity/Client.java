package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
//TODO: add JPA annotations
@Table
public class Client {
    private String clientId;
    private String companyName;
    private String vatNumber;
    private String addressId;
}
