package com.paymentgateway.gpay.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ResponseHelper {
    
    @Schema(description = "Timestamp", example = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private Date timeStamp = new Date();

    @Parameter(name = "responseCode", example = "200")
    private int responseCode;

    @Schema(description = "Status", example = "true")
    private Boolean status;

    @Schema(description = "Message", example = "Successful")
    private String message;

    @Schema(description = "Response Entity", example = "null")
    private Object data;

    public ResponseHelper(Boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
