package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;
    private Boolean primaryAddress;
}
