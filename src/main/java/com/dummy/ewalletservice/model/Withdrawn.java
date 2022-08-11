package com.dummy.ewalletservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "withdrawn")
public class Withdrawn {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "wallet_id")
    private String walletId;

    @Column(name = "withdrawn_at")
    private Timestamp withdrawnAt;

    @Column(name = "reference_id")
    private String referenceId;

    private BigDecimal amount;

}
