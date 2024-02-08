package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class User {

    private UUID userId;

    private Boolean verified;

    private String firstName;

    private String contactDetailsId;

    private String password;

    private String position;

    private String role;

    private UUID emergencyContactId;
}
