package com.paymentgateway.gpay.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity

public class TransactionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tran_id_seq")
    @SequenceGenerator(name = "tran_id_seq", sequenceName = "tran_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String paymentReference;

    private String responseCode;

    private String description;

    private LocalDateTime transDate;
    private BigDecimal tranAmount;
    private boolean tranStatus = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
