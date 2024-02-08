package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "address_id", columnDefinition = "BINARY(16)")
    private String addressId;

    @Column(name = "building_or_unit_number")
    private String buildingUnitNumber;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "district_or_suburb")
    private String districtSuburb;

    @Column(name = "city")
    private String city;

    @Column(name = "state_or_province")
    private String stateProvince;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;
}
