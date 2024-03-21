package com.paymentgateway.gpay.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionStatusQueryRequest {

    @Schema(description = "Transaction DateTime", example = "2024-07-13 17:59:36")
    private LocalDateTime transactionDateTime;

    @Schema(description = "Transaction Reference", example = "888001230713212850000665732924")
    private String transactionReference;
}
