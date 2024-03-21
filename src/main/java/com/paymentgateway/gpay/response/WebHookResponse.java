package com.paymentgateway.gpay.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WebHookResponse {
    private String paymentReference;

    @Schema(description = "paymentReference", example = "successful")
    private String eventType;
    private String responseCode;
    private String description;
    private LocalDateTime transDate;
    private BigDecimal tranAmount;
    private boolean tranStatus;

    public WebHookResponse(String eventType,String paymentReference, String responseCode, String description, LocalDateTime transDate, BigDecimal tranAmount, boolean tranStatus) {
        this.eventType = eventType;
        this.paymentReference = paymentReference;
        this.responseCode = responseCode;
        this.description = description;
        this.transDate = transDate;
        this.tranAmount = tranAmount;
        this.tranStatus = tranStatus;
    }
}
