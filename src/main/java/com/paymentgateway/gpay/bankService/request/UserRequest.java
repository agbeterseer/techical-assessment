package com.paymentgateway.gpay.bankService.request;

import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phoneNumber;
    private String email;
    private String ip;
}
