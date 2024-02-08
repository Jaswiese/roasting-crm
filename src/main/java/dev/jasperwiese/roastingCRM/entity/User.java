package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
@Entity
public class User {

    @Id
    @Column(name = "user_id", columnDefinition = "Binary(16)")
    private UUID userId;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "first_name")
    private String firstName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_details_id")
    private ContactDetails contactDetails;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<UserAddress> addresses;

    @Column(name = "password")
    private String password;

    @Column(name = "position")
    private String position;

    @Column(name = "role")
    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emergency_contact_id")
    private EmergencyContact emergencyContact;
}
