package com.paymentgateway.gpay.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransactionStatusQueryResponse {

    @Schema(description = "responseCode", example = "00")
    private String responseCode;

    @Schema(description = "responseCode", example = "Approved or completed successfully")
    private String responseMessage;

    @Schema(description = "paymentReference", example = "888001230713212850000665732924")
    private String paymentReference;
}
