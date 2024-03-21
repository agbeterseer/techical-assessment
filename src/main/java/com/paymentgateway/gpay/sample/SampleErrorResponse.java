package com.paymentgateway.gpay.sample;

import com.paymentgateway.gpay.util.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.Parameter;
@Getter
@Setter
@NoArgsConstructor
public class SampleErrorResponse {

    @Parameter(name = "timeStamp", example = "2021-05-16 11:28:45")
    private String timeStamp;
    @Parameter(name = "responseCode", example = "404")
    private int responseCode;

    @Parameter(name = "status", example = "false")
    private boolean status;

    @Parameter(name = "message", example = Constant.ERROR_MESSAGE)
    private String message;

    @Parameter(name = "data", example = "null")
    private String data;
}
