package com.paymentgateway.gpay.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TxnNotificationRequest {
    private String responseCode;
    private String sessionId;
    private String eventType;
    private boolean status;
    private String transactionDate;
    private BigDecimal amount;
    private String description;
}
