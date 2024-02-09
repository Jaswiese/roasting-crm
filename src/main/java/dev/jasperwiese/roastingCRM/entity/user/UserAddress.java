package dev.jasperwiese.roastingCRM.entity.user;

import dev.jasperwiese.roastingCRM.entity.Address;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_address")
public class UserAddress {

    @EmbeddedId
    private UserAddressPK id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean primaryAddress;

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", primaryAddress=" + primaryAddress +
                '}';
    }
}
