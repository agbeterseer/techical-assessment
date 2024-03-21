package com.paymentgateway.gpay.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentResponse {
    @Schema(description = "ResponseCode", example = "200")
    private String responseCode;

    @Schema(description = "Message", example = "payment completed successfully")
    private String responseMessage;

    private boolean success;

    @Schema(description = "TransactionId", example = "32423423423432932")
    private String transactionId;

    public PaymentResponse(String responseCode, String responseMessage, String transactionId) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.transactionId = transactionId;
    }
}