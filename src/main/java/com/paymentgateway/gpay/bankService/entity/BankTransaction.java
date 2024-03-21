package com.paymentgateway.gpay.bankService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_transactions")
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_tran_id_seq")
    @SequenceGenerator(name = "acc_tran_id_seq", sequenceName = "acc_tran_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private String sessionId;

    @NotNull
    private String acctNum;

    @NotNull
    private BigDecimal tranAmount;

    private BigDecimal chargeAmount= new BigDecimal("0.00");

    private BigDecimal vatAmount=new BigDecimal("0.00");


    private String transactionType;

    @NotNull
    private String partTranType;

    private String tranCrncy;


    private String senderName;

    private String receiverName;

    private String transChannel;


    @Column(nullable = true)
    private Integer tranPart;
    @NotNull
    private String tranNarrate;



    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public BankTransaction(String sessionId, String acctNum, BigDecimal tranAmount, BigDecimal chargeAmount, BigDecimal vatAmount, String transactionType, String partTranType, String tranCrncy, String senderName, String receiverName, String tranNarrate, Integer tranPart, LocalDateTime createdAt) {

        this.sessionId = sessionId;
        this.acctNum = acctNum;
        this.tranAmount = tranAmount;
        this.chargeAmount = chargeAmount;
        this.vatAmount = vatAmount;
        this.transactionType = transactionType;
        this.partTranType = partTranType;
        this.tranCrncy = tranCrncy;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.tranNarrate = tranNarrate;
        this.tranPart = tranPart;
        this.createdAt = createdAt;
    }
}
