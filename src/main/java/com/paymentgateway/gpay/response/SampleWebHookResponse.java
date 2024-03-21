package com.paymentgateway.gpay.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SampleWebHookResponse {
    @Schema(description = "paymentReference", example = "8889878742322233333333232")
    private String paymentReference;

    @Schema(description = "paymentReference", example = "successful")
    private String eventType;
    @Schema(description = "responseCode", example = "00")
    private String responseCode;
    @Schema(description = "description", example = "payment completed successfully")
    private String description;
    @Schema(description = "transDate", example = "2024-12-12")
    private LocalDateTime transDate;
    @Schema(description = "tranAmount", example = "3000.00")
    private BigDecimal tranAmount;
    @Schema(description = "tranStatus", example = "true")
    private boolean tranStatus;
}
