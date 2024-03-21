package com.paymentgateway.gpay.bankService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "bank_users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_tran_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_tran_id_seq", allocationSize = 1)
    private Long id;

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

    public User(Long id) {
        this.id = id;
    }
}
