package com.paymentgateway.gpay.bankService.repository;

import com.paymentgateway.gpay.bankService.entity.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {

    CardInfo findCardInfosByCardNumber(String cardNumber);
}
