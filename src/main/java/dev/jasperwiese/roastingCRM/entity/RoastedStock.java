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
public class RoastedStock {

    private String roastedStockId;

    private String unroastedStockId;

    private String roastingProfileId;

    private String userId;

    private ZonedDateTime dateRoasted;

    private BigInteger weightBeforeRoasted;

    private BigInteger weightAfterRoasted;

    private BigInteger weightRemaining;


}
