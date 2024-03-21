package com.paymentgateway.gpay.controller;

import com.paymentgateway.gpay.exception.CustomException;
import com.paymentgateway.gpay.request.PaymentRequest;
import com.paymentgateway.gpay.request.TransactionStatusQueryRequest;
import com.paymentgateway.gpay.request.TxnNotificationRequest;
import com.paymentgateway.gpay.response.*;
import com.paymentgateway.gpay.sample.BadRequestResponse;
import com.paymentgateway.gpay.sample.SamplePaymentResponse;
import com.paymentgateway.gpay.sample.SampleStatusQueryResponse;
import com.paymentgateway.gpay.sample.UnAuthorizedResponse;
import com.paymentgateway.gpay.service.gateway.core.GpayGateway;
import com.paymentgateway.gpay.util.Constant;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PAYMENT GATEWAY", description = "Facilitating transactions between a retail application\n" +
        "and a banking service: PAYMENT GATEWAY Service API")
@OpenAPIDefinition(info = @Info(title = "PAYMENT GATEWAY", version = "1.0", description = "PAYMENT GATEWAY MIDDLEWARE API documentation"))
@RestController
@Validated
@RequestMapping(Constant.API_V1)
public class PaymentGatewayController {

    private final GpayGateway gateway;

    @Autowired
    public PaymentGatewayController(GpayGateway gateway) {
        this.gateway = gateway;
    }

    @Order(1)
    @Operation(summary = "Payment endpoint", description = "Payment endpoint initiates payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful", content = @Content(schema = @Schema(implementation = SamplePaymentResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = UnAuthorizedResponse.class)))
    })
    @PostMapping("/pay")
    ResponseEntity<ResponseHelper> initPayment(@Valid @RequestBody PaymentRequest paymentRequest){
        try {
            PaymentResponse paymentResponse = gateway.initPayment(paymentRequest);

            if(paymentResponse.getResponseCode() == "00"){
                return ResponseEntity.ok(new ResponseHelper(true, paymentResponse.getResponseMessage(), paymentResponse));
            }
            return ResponseEntity.ok(new ResponseHelper(false, paymentResponse.getResponseMessage(), paymentResponse));


        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
        }
    }

    @Order(2)
    @Operation(summary = "Transaction status endpoint", description = "This endpoint is used for checking transaction status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Approved or completed successfully", content = @Content(schema = @Schema(implementation = SampleStatusQueryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = UnAuthorizedResponse.class)))
    })
    @PostMapping("/paymentStatus")
    ResponseEntity<ResponseHelper> paymentConfirmation(@Valid @RequestBody TransactionStatusQueryRequest request){
        TransactionStatusQueryResponse response = gateway.paymentConfirmation(request);

        if(response.getResponseCode() == "00"){
            return ResponseEntity.ok(new ResponseHelper(true, response.getResponseMessage(), response));
        }
        return ResponseEntity.ok(new ResponseHelper(false, response.getResponseMessage(), response));
    }

    @Order(2)
    @Operation(summary = "WebHook Notification endpoint", description = "This endpoint is used for receiving webhook notifications for transaction updates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Approved or completed successfully", content = @Content(schema = @Schema(implementation = SampleWebHookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = UnAuthorizedResponse.class)))
    })
    @PostMapping("/webhook/transaction")
    public ResponseEntity<ResponseHelper> handleTransactionWebhook(@RequestBody TxnNotificationRequest payload) {
        // Parse the incoming payload and process the webhook notification
        // You can deserialize the payload into an object and perform necessary actions
        System.out.println("Received transaction webhook notification: " + payload);
        WebHookResponse response = gateway.webhook(payload);

        // Respond with a success status code
        if(response.getResponseCode() == "00"){
            return ResponseEntity.ok(new ResponseHelper(true, response.getDescription(), response));
        }
        return ResponseEntity.ok(new ResponseHelper(false, response.getDescription(), response));
    }

}
