package dev.jasperwiese.roastingCRM.entity;

import jakarta.persistence.Table;
import lombok.*;

import java.math.BigInteger;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//TODO: add JPA annotations
@Table
public class UnroastedStock {

    private String unroastedStockId;

    private String greenBeansId;

    private String supplierId;

    private ZonedDateTime dateArrived;

    private BigInteger weightAtArrival;

    private BigInteger weightAtRemaining;

    private BigInteger cost;

    private String notes;
}
