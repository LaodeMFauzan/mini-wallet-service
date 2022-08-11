package com.dummy.ewalletservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "deposited_at")
    private Timestamp depositedAt;

    @Column(name = "reference_id")
    private String referenceId;

    private BigDecimal amount;
}
