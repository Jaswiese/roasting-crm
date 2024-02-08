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
public class Order {

    private String orderId;

    private String roastedStockId;

    private String clientId;

    private String addressId;

    private BigInteger weight;

    private Boolean needToShip;

    private Boolean multiOrder;

    private String orderCollective;

    private ZonedDateTime orderCreatedDate;

    private ZonedDateTime orderDeliveryDate;
}
