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
@Table(name = "green_beans")
public class GreenBeans {

    @Id
    @Column(name = "green_beans_id")
    private UUID greenBeansId;

    @Column(name = "field")
    private String field;

    @Column(name = "region")
    private String region;

    @Column(name = "grade")
    private String grade;

    @Column(name = "flavour")
    private String flavour;

    @Column(name = "body")
    private String body;

    @Column(name = "acidity")
    private String acidity;

    @Column(name = "process")
    private String process;

    @Column(name = "moisture")
    private String moisture;

    @Column(name = "packaging")
    private String packaging;

    @Column(name = "notes")
    private String notes;


}
