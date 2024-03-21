package com.paymentgateway.gpay.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SampleStatusQueryResponse {
    @Schema(description = "responseCode", example = "00")
    private String responseCode;

    @Schema(description = "responseCode", example = "Approved or completed successfully")
    private String responseMessage;

    @Schema(description = "paymentReference", example = "888001230713212850000665732924")
    private String paymentReference;

    public SampleStatusQueryResponse() {
        this.responseCode = "00";
        this.responseMessage = "Approved or completed successfully";
        this.paymentReference = "888001230713212850000665732924";
    }
}
