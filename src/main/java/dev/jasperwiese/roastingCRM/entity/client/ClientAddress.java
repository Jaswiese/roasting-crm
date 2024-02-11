package dev.jasperwiese.roastingCRM.entity.client;

import dev.jasperwiese.roastingCRM.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_address")
public class ClientAddress {
    @EmbeddedId
    private ClientAddressPK id;
    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @MapsId("addressId")
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean primaryAddress;

    private String name;

    @Override
    public String toString() {
        return "ClientAddress{" +
                "id=" + id +
                ", primaryAddress=" + primaryAddress +
                ", name='" + name + '\'' +
                '}';
    }
}
