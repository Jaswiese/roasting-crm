package dev.jasperwiese.roastingCRM.entity.user;

import dev.jasperwiese.roastingCRM.entity.ContactDetails;
import dev.jasperwiese.roastingCRM.entity.EmergencyContact;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User {

    @Id
    @Column(
            name = "user_id",
            columnDefinition = "Binary(16)"
    )
    private UUID userId;

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "contact_details_id")
    private ContactDetails contactDetails;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserAddress> addresses = new ArrayList<>();

    @Column(name = "password")
    private String password;

    @Column(name = "position")
    private String position;

    @Column(name = "role")
    private String role;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "emergency_contact_id")
    private EmergencyContact emergencyContact;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", verified=" + verified +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", role='" + role + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", addresses='" + addresses + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                '}';
    }
}
