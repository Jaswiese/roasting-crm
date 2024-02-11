package dev.jasperwiese.roastingCRM.entity.client;

import dev.jasperwiese.roastingCRM.entity.ContactPerson;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client_contacts")
public class ClientContacts {

    @EmbeddedId
    private ClientContactPK id;

    @ManyToOne
    @MapsId("contactPersonId")
    @JoinColumn(name = "contact_person_id")
    private ContactPerson contactPerson;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    private Client client;

    private Boolean preferredContact;

    private String notes;

    @Override
    public String toString() {
        return "ClientContacts{" +
                "id=" + id +
                ", preferredContact=" + preferredContact +
                ", notes='" + notes + '\'' +
                '}';
    }
}
