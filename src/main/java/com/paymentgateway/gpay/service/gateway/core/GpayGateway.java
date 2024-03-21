package com.paymentgateway.gpay.service.gateway.core;

import com.paymentgateway.gpay.bankService.BankingService;
import com.paymentgateway.gpay.bankService.response.BankServiceResponse;
import com.paymentgateway.gpay.entity.TransactionLog;
import com.paymentgateway.gpay.enums.EventType;
import com.paymentgateway.gpay.repository.TransactionLogRepository;
import com.paymentgateway.gpay.request.PaymentRequest;
import com.paymentgateway.gpay.request.TransactionStatusQueryRequest;
import com.paymentgateway.gpay.request.TxnNotificationRequest;
import com.paymentgateway.gpay.response.PaymentResponse;
import com.paymentgateway.gpay.response.TransactionStatusQueryResponse;
import com.paymentgateway.gpay.response.WebHookResponse;
import com.paymentgateway.gpay.util.Helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class GpayGateway extends Gateway {


    @Value("${bankCode}")
    String bankCode;


    private final BankingService bankingService;
    private final TransactionLogRepository transactionLogRepository;


    @Autowired
    public GpayGateway(BankingService bankingService, TransactionLogRepository transactionLogRepository) {
        this.bankingService = bankingService;
        this.transactionLogRepository = transactionLogRepository;
    }


    @Override
    public PaymentResponse initPayment(PaymentRequest  paymentRequest) {

        String payload = Helper.processEncryption(paymentRequest);

        String sessionId = Helper.generateSessionId(bankCode);

        logRequest(paymentRequest,sessionId); // logTransaction
        BankServiceResponse bankServiceResponse = bankingService.processPayment(payload, sessionId);
        logRequestUpdate(bankServiceResponse); // logTransaction

        // call bankServices
        return new PaymentResponse(bankServiceResponse.getResponseCode(),bankServiceResponse.getResponseMessage(),bankServiceResponse.getPaymentReference());
    }

    @Override
    public TransactionStatusQueryResponse paymentConfirmation(TransactionStatusQueryRequest queryRequest) {
        TransactionStatusQueryResponse response = new TransactionStatusQueryResponse();
        try{
            Optional<TransactionLog> transactionLog = transactionLogRepository.findTransactionLogByPaymentReferenceAndAndTransDate(queryRequest.getTransactionReference(), queryRequest.getTransactionDateTime());

            if(transactionLog.isPresent()){
                TransactionLog transactionLog1 = transactionLog.get();
                response.setPaymentReference(transactionLog1.getPaymentReference());
                response.setResponseCode(transactionLog1.getResponseCode());
                response.setResponseMessage(transactionLog1.getDescription());
            }
        }catch (Exception ex){
           ex.printStackTrace();
       }
        return response;
    }

    @Override
    public WebHookResponse webhook(TxnNotificationRequest request) {

      Optional<TransactionLog> transactionLog =  transactionLogRepository.findTransactionLogByPaymentReference(request.getSessionId());

        if(transactionLog.isPresent()){
            TransactionLog transactionLog1 = transactionLog.get();
            if(EventType.SUCCESSFUL.name().equalsIgnoreCase(request.getEventType())){
                if(Objects.equals(request.getResponseCode(), "00")){
                    transactionLog1.setResponseCode(request.getResponseCode());
                    transactionLog1.setDescription(transactionLog1.getDescription());
                    transactionLog1.setUpdatedAt(LocalDateTime.now());
                    transactionLog1.setTranStatus(true);
                    transactionLogRepository.save(transactionLog1);
                }
            } else if (EventType.FAILED.name().equalsIgnoreCase(request.getEventType())) {
                    transactionLog1.setResponseCode(request.getResponseCode());
                    transactionLog1.setDescription(transactionLog1.getDescription());
                    transactionLog1.setUpdatedAt(LocalDateTime.now());
                    transactionLog1.setTranStatus(false);
                    transactionLogRepository.save(transactionLog1);
            }else {
                transactionLog1.setResponseCode(request.getResponseCode());
                transactionLog1.setDescription(transactionLog1.getDescription());
                transactionLog1.setUpdatedAt(LocalDateTime.now());
                transactionLog1.setTranStatus(false);
                transactionLogRepository.save(transactionLog1);
            }
            return new WebHookResponse(request.getEventType(), transactionLog1.getPaymentReference(), transactionLog1.getResponseCode(), transactionLog1.getDescription(), transactionLog1.getTransDate(), transactionLog1.getTranAmount(), transactionLog1.isTranStatus());
        }
        return new WebHookResponse();
    }


    private void logRequest(PaymentRequest request, String sessionId){
        try {
            TransactionLog transactionLog = new TransactionLog();
            transactionLog.setCreatedAt(LocalDateTime.now());
            transactionLog.setPaymentReference(sessionId);
            transactionLog.setTranAmount(request.getAmount());
            transactionLogRepository.save(transactionLog);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void logRequestUpdate(BankServiceResponse response){
        try {
            Optional<TransactionLog> transaction =  transactionLogRepository.findTransactionLogByPaymentReference(response.getPaymentReference());
         if(transaction.isPresent()){
             if(Objects.equals(response.getResponseCode(), "00")){
                 TransactionLog transactionLog = transaction.get();
                 transactionLog.setTranStatus(true);
                 transactionLog.setUpdatedAt(LocalDateTime.now());
                 transactionLog.setDescription("payment completed successfully");
                 transactionLogRepository.save(transactionLog);
             }
         }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
