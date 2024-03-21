package com.paymentgateway.gpay.enums;

import java.util.Optional;

public enum TransStatus {
    //// PENDING  // REVERSED  // SUCCESSFUL

    PENDING,
    REVERSED,
    SUCCESSFUL;

    public static Optional<TransStatus> find(String value) {
        if (isNonEmpty(value)) {
            try {
                return Optional.of(TransStatus.valueOf(value.toUpperCase()));
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