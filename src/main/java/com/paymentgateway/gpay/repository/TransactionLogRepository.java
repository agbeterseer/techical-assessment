package com.paymentgateway.gpay.repository;

import com.paymentgateway.gpay.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {

    Optional<TransactionLog> findTransactionLogByPaymentReference(String paymentReference);

    Optional<TransactionLog> findTransactionLogByPaymentReferenceAndAndTransDate(String paymentReference, LocalDateTime transDate);
}
