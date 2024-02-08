package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//TODO: add JPA annotations
@Entity
@Table(name = "contact_details")
public class ContactDetails {

    @Id
    @Column(name = "contact_details_id", columnDefinition = "Binary(16)")
    private String contactDetailsId;

    @Column(name = "email")
    private String email;

    @Column(name = "busines_email")
    private String businessEmail;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "personal_phone")
    private String personalPhone;
}
