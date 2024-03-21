package com.paymentgateway.gpay.service.gateway.core;

import com.paymentgateway.gpay.request.PaymentRequest;
import com.paymentgateway.gpay.request.TransactionStatusQueryRequest;
import com.paymentgateway.gpay.request.TxnNotificationRequest;
import com.paymentgateway.gpay.response.PaymentResponse;
import com.paymentgateway.gpay.response.TransactionStatusQueryResponse;
import com.paymentgateway.gpay.response.WebHookResponse;

public abstract class Gateway {

    /**
     * This method is used for initiating a transaction,
     *
     */
    public abstract PaymentResponse initPayment(PaymentRequest paymentRequest);

    /**
     * This method is used to checking transaction status,
     *
     */
    public abstract TransactionStatusQueryResponse paymentConfirmation(TransactionStatusQueryRequest queryRequest);

    /**
     * This moethod is for receiving webhook notifications for transaction updates.
     */
    public abstract WebHookResponse webhook(TxnNotificationRequest request);

}
