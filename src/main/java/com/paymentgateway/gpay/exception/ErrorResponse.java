package com.paymentgateway.gpay.exception;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;

@XmlRootElement(name="error")
public class ErrorResponse{
    private Date date;
    private int value;
    private String message;
    private List<String> details;

    public ErrorResponse(Date date, List<String> details, int value, String message) {

        this.date = date;
        this.details = details;
        this.value = value;
        this.message = message;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}

