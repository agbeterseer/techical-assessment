package com.paymentgateway.gpay.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {


    @NotBlank(message="please provide OrderId")
    private String orderId;

    @NotNull
    @DecimalMin(value = "0", inclusive = true, message = "Value must be greater than or equal to zero")
    private BigDecimal amount;

    private String currency;

    @NotBlank(message="please provide Card Name")
    private String cardName;

    @NotBlank(message="please provide Card Number")
    private String cardNumber;

    @NotBlank(message="please provide Expiry Date")
    private String expiryDate;

    private int cvv;
}