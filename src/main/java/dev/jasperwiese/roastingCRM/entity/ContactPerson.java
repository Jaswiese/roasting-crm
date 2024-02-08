package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class ContactPerson {

    private String contactPersonId;

    private String firstName;

    private String lastName;

    private String positionHeld;

    private String contactDetailsId;

}
