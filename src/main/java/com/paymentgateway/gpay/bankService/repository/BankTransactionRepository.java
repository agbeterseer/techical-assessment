package com.paymentgateway.gpay.bankService.repository;

import com.paymentgateway.gpay.bankService.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {
}
