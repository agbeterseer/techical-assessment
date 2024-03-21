package com.paymentgateway.gpay.bankService.repository;

import com.paymentgateway.gpay.bankService.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNo(String accountNo);
}
