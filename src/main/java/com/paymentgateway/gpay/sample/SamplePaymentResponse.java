package com.paymentgateway.gpay.sample;

import com.paymentgateway.gpay.response.PaymentResponse;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class SamplePaymentResponse {

    @Schema(description = "timeStamp", example = "2021-05-16 11:28:45")
    private String timeStamp;

    @Schema(description = "status", example = "true")
    private boolean status;

    @Schema(description = "message", example = "Successful")
    private String message;

    @Schema(description = "data", example =
            "{\n" +
            "        \"responseCode\": \"00\",\n" +
            "        \"responseMessage\": \"payment completed successfully\",\n" +
            "        \"success\": \"true\",\n" +
            "        \"transactionId\": \"888001230713212850000665732924\"\n" +
            "}")
    private Object data;

    public SamplePaymentResponse() {
        this.timeStamp = "";
        this.status = true;
        this.message = "Successful";
        this.data = PaymentResponse.class;
    }
}

