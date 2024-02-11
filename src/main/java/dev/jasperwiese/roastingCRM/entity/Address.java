package dev.jasperwiese.roastingCRM.entity;

import dev.jasperwiese.roastingCRM.entity.client.ClientAddress;
import dev.jasperwiese.roastingCRM.entity.user.UserAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private UUID addressId;

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

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserAddress> userAddresses = new ArrayList<>();

    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientAddress> clientAddresses = new ArrayList<>();
}
