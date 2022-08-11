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
@Table(name = "wallet")
public class Wallet {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "uuid", unique = true)
    private String id;

    @Column(name = "customer_id")
    private String customerId;

    private Boolean status;

    @Column(name = "enabled_at")
    private Timestamp enabledAt;

    @Column(name = "disabled_at")
    private Timestamp disabledAt;

    private BigDecimal balance;
}
