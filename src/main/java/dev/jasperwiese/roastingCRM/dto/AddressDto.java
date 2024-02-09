package dev.jasperwiese.roastingCRM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String addressId;
    private String buildingUnitNumber;
    private String streetAddress;
    private String districtSuburb;
    private String city;
    private String stateProvince;
    private String postalCode;
    private String country;
}
