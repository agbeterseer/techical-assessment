package com.paymentgateway.gpay.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {
    private Long id;

    private String status;
    private String paymentReference;
    private BigDecimal amount;

}
