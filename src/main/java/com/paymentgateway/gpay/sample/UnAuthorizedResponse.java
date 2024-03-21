package com.paymentgateway.gpay.sample;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UnAuthorizedResponse {
    @Schema(description = "Timestamp of the error", example = "2022-01-01T12:00:00Z")
    private String timeStamp;

    @Schema(description = "HTTP response code", example = "401")
    private int responseCode;

    @Schema(description = "Status of the response", example = "false")
    private boolean status;

    @Schema(description = "Error message", example = "Unauthorized")
    private String message;

    @Schema(description = "Additional data", example = "")
    private Object data;

    // Constructors
    public UnAuthorizedResponse() {
        this.timeStamp = "2022-01-01T12:00:00Z"; // Set default timestamp
        this.responseCode = 401;
        this.status = false;
        this.message = "Unauthorized";
        this.data = null; // Set default data
    }
}
