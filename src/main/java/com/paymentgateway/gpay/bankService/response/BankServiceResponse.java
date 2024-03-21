package com.paymentgateway.gpay.bankService.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankServiceResponse {
    private String responseCode;
    private String responseMessage;
    private String paymentReference;
}
