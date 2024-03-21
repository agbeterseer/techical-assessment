package com.paymentgateway.gpay.client.enums;


import java.util.Optional;

public enum EventType {

    PAYMENT_PROCESSED("payment_processed"),
    REVERSED("refund_processed");

    private String value;
    EventType(String value) {
      this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<EventType> find(String value) {
        if (isNonEmpty(value)) {
            try {
                return Optional.of(EventType.valueOf(value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
    public static boolean isNonEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
