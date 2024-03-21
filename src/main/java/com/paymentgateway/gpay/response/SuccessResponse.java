package com.paymentgateway.gpay.response;

import com.paymentgateway.gpay.util.Constant;

public class SuccessResponse extends ResponseHelper{

    public SuccessResponse(String message, Object data){
        super(true, message, data);
    }

    public SuccessResponse(Object data, boolean status){
        super(true, Constant.SUCCESS_MESSAGE, data);
    }

}
