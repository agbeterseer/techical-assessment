package com.paymentgateway.gpay.response;

import com.paymentgateway.gpay.util.Constant;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

public class ErrorResponse extends ResponseHelper {

private HttpStatus httpStatus;
    public ErrorResponse(String message){
        super(false, message, Strings.EMPTY);
    }

    public ErrorResponse(){
        super(false, Constant.ERROR_MESSAGE, Strings.EMPTY);
    }

}
