package com.paymentgateway.gpay.sample;

import com.paymentgateway.gpay.response.PaymentResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class NotFoundResponse {

    @Schema(description = "Timestamp of the error", example = "2022-01-01T12:00:00Z")
    private String timeStamp;

    @Schema(description = "HTTP response code", example = "404")
    private int responseCode;

    @Schema(description = "Status of the response", example = "true")
    private boolean status;

    @Schema(description = "Error message", example = "Resource not found")
    private String message;

    @Schema(description = "Additional data", example = "")
    private Object data;

    // Constructors
    public NotFoundResponse() {
        this.timeStamp = "2022-01-01T12:00:00Z"; // Set default timestamp
        this.responseCode = 404;
        this.status = true;
        this.message = "Resource not found";
        this.data = null; // Set default data
    }

    // Getters and setters
}
