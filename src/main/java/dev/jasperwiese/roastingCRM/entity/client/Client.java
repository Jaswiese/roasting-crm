package dev.jasperwiese.roastingCRM.entity.client;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "business_client")
public class Client {

    @Id
    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "vat_number")
    private String vatNumber;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientAddress> addresses = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientContacts> clientContacts = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientRoastingProfiles> clientRoastingProfiles = new ArrayList<>();
}
