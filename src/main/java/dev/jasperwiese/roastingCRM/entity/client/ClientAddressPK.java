package dev.jasperwiese.roastingCRM.entity.client;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClientAddressPK implements Serializable {

    private UUID clientId;
    private UUID addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAddressPK that = (ClientAddressPK) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(addressId, that.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, addressId);
    }
}
