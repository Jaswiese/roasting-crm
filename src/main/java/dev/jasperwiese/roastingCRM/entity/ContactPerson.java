package dev.jasperwiese.roastingCRM.entity;

import dev.jasperwiese.roastingCRM.entity.client.ClientContact;
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
@Table(name = "contact_person")
public class  ContactPerson {

    @Id
    @Column(
            name = "contact_person_id",
            columnDefinition = "Binary(16)"
    )
    private UUID contactPersonId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "position_held")
    private String positionHeld;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "contact_details_id")
    private ContactDetails contactDetails;

    @OneToMany(
            mappedBy = "contactPerson",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientContact> clientContacts = new ArrayList<>();

}
