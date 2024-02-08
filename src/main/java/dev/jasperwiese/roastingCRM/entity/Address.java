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
public class Address {

    private String addressId;

    private String buildingUnitNumber;

    private String streetAddress;

    private String districtSuburb;

    private String city;

    private String stateProvince;

    private String postalCode;

    private String country;
}
