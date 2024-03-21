package com.paymentgateway.gpay.enums;

public enum EventType {
    PENDING("pending"),
    SUCCESSFUL("successful"),
    FAILED("failed");

    String value;
    EventType(String value) {
        this.value = value;
    }


}
