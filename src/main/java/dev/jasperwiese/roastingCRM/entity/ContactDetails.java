package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "contact_details")
public class ContactDetails {

    @Id
    @Column(name = "contact_details_id", columnDefinition = "Binary(16)")
    private UUID contactDetailsId;

    @Column(name = "email")
    private String email;

    @Column(name = "busines_email")
    private String businessEmail;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "personal_phone")
    private String personalPhone;
}
