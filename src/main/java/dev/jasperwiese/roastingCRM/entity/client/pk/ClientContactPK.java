package dev.jasperwiese.roastingCRM.entity.client.pk;

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
public class ClientContactPK implements Serializable {

    private UUID clientId;
    private UUID contactPersonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientContactPK that = (ClientContactPK) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(contactPersonId, that.contactPersonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, contactPersonId);
    }
}
