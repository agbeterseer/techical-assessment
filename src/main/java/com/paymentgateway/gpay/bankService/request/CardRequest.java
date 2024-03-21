package com.paymentgateway.gpay.bankService.request;

import lombok.Data;

@Data
public class CardRequest {
    private String accountNo;
    private String cardName;
    private String cardNumber;
    private String expiryDate;
    private int cvv;
}
