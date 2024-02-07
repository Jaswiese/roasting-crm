package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class User {
    private String userId;

    private Boolean verified;

    private String firstName;

    private String contactDetailsId;

    private String password;

    private String position;

    private String role;

    private String emergencyContactId;
}
