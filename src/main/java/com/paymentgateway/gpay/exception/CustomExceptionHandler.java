package com.paymentgateway.gpay.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return getObjectResponseEntity(request, ex.getLocalizedMessage(), ex.getMessage(), (CustomException) ex);
    }


    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleInvoiceException(CustomException ex, WebRequest request) {
        return getObjectResponseEntity(request, ex.getLocalizedMessage(), ex.getMessage(), ex);
    }

    private ResponseEntity<Object> getObjectResponseEntity(WebRequest request, String localizedMessage, String message, CustomException ex) {
        List<String> details = new ArrayList<>();
        details.add(localizedMessage);
        details.add(request.getDescription(false));
        details.add(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(new Date(), details, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add(request.getDescription(false));
        ErrorResponse errorResponse = new ErrorResponse(new Date(), details, HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public final ResponseEntity<Object> handleUserNotFoundException(HttpClientErrorException.BadRequest ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add(request.getDescription(false));
        ErrorResponse errorResponse = new ErrorResponse(new Date(), details, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
