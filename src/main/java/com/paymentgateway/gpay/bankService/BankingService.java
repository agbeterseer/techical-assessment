package com.paymentgateway.gpay.bankService;

import com.paymentgateway.gpay.bankService.response.BankServiceResponse;

public interface BankingService {
    BankServiceResponse processPayment(String payload, String sessionId);

}
