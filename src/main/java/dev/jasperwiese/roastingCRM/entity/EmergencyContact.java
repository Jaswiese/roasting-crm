package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//TODO: add JPA annotations support
@Table
public class EmergencyContact {

    private UUID emergencyContactId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String relationship;

}
