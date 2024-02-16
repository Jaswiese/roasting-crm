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
public class ClientRoastingProfilesPK implements Serializable {

    private UUID clientId;
    private UUID roastingProfileId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientRoastingProfilesPK that = (ClientRoastingProfilesPK) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(roastingProfileId, that.roastingProfileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, roastingProfileId);
    }
}
