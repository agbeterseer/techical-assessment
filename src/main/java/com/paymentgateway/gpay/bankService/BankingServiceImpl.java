package com.paymentgateway.gpay.bankService;

import com.paymentgateway.gpay.bankService.entity.Account;
import com.paymentgateway.gpay.bankService.entity.BankTransaction;
import com.paymentgateway.gpay.bankService.entity.CardInfo;
import com.paymentgateway.gpay.bankService.repository.AccountRepository;
import com.paymentgateway.gpay.bankService.repository.BankTransactionRepository;
import com.paymentgateway.gpay.bankService.repository.CardInfoRepository;
import com.paymentgateway.gpay.bankService.response.BankServiceResponse;
import com.paymentgateway.gpay.exception.CustomException;
import com.paymentgateway.gpay.request.PaymentRequest;
import com.paymentgateway.gpay.util.Constant;
import com.paymentgateway.gpay.util.Helper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class BankingServiceImpl implements BankingService {



    @Value("${merchantCreditAccount}")
    String merchantCreditAccount;


    private final AccountRepository accountRepository;
    private final CardInfoRepository cardInfoRepository;
    private final BankTransactionRepository bankTransactionRepository;

    @Autowired
    public BankingServiceImpl(AccountRepository accountRepository, CardInfoRepository cardInfoRepository, BankTransactionRepository bankTransactionRepository) {
        this.accountRepository = accountRepository;
        this.cardInfoRepository = cardInfoRepository;
        this.bankTransactionRepository = bankTransactionRepository;
    }


    private void validateRequest(PaymentRequest payload){

        if(Strings.isBlank(payload.getOrderId())){
            throw new CustomException("Order Id not be null", HttpStatus.BAD_REQUEST);
        }

        if(payload.getAmount().compareTo(BigDecimal.valueOf(0)) == 0){
            throw new CustomException("Amount must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        String cvvString = String.valueOf(payload.getCvv());
        if(cvvString.length() < 3 || cvvString.length() > 3 ){
            throw new CustomException("CVV cannot be more than 3 digits", HttpStatus.BAD_REQUEST);
        }

        if(payload.getCardNumber().length() == 15){
            throw new CustomException("CArd cannot be more than 3 digits", HttpStatus.BAD_REQUEST);
        }

        if(payload.getExpiryDate().length() == 5){
            throw new CustomException("Expiry date should be 12/25", HttpStatus.BAD_REQUEST);
        }
    }

    public BankServiceResponse processPayment(String payload, String sessionId){

        PaymentRequest paymentRequest = Helper.jsonToObject(Helper.processDecryption(payload));

        validateRequest(paymentRequest);

        Account account = getCustomerAccountDetails(paymentRequest);
        if(account == null)
            return new BankServiceResponse("07", "Invalid Account", sessionId);

        BankServiceResponse check = Helper.simpleFraudCheck(account);
        if(check.getResponseCode() !=null){
            check.setPaymentReference(sessionId);
            return check;
        }

        // do debit and credit
        return doPostTransaction(paymentRequest,account, sessionId);
        // return response to payment Gateway
    }

    public Account getCustomerAccountDetails(PaymentRequest request){
        Account account = null;
        CardInfo cardInfo = cardInfoRepository.findCardInfosByCardNumber(request.getCardNumber());
        if(cardInfo !=null){
            account = accountRepository.findAccountByAccountNo(cardInfo.getAccountNo());
        }
        return account;
    }

    public Account getMerchantAccountDetails(){
        Account account = null;
        if(merchantCreditAccount !=null){
            account = accountRepository.findAccountByAccountNo(merchantCreditAccount);
        }
        return account;
    }


    private BankServiceResponse doPostTransaction(PaymentRequest request, Account accountDebit, String sessionId){
        // generate sessionId
        BankServiceResponse response = null;
        try {

            int n = 1;

            Account accountCredit = getMerchantAccountDetails();

            BankTransaction debit = new BankTransaction(sessionId, accountDebit.getAccountNo(), request.getAmount(), new BigDecimal("0.00"), new BigDecimal("0.00"),  "CARD", "D", "NGN", request.getCardName(), getMerchantAccountDetails().getAcct_name(), Constant.tranNarrate, n, LocalDateTime.now());

            n = n + 1;
            BankTransaction credit = new BankTransaction(sessionId, merchantCreditAccount, request.getAmount(), new BigDecimal("0.00"), new BigDecimal("0.00"),  "CARD", "C", "NGN", request.getCardName(), getMerchantAccountDetails().getAcct_name(), Constant.tranNarrate,n, LocalDateTime.now());

            bankTransactionRepository.saveAndFlush(debit);
            bankTransactionRepository.saveAndFlush(credit);

            BigDecimal clrBalAmtDr = accountDebit.getClr_bal_amt().subtract(request.getAmount());
            BigDecimal cumBalDrAmtDr = accountDebit.getCum_dr_amt().add(request.getAmount());

            accountDebit.setClr_bal_amt(clrBalAmtDr);
            accountDebit.setCum_dr_amt(cumBalDrAmtDr);
            accountRepository.saveAndFlush(accountDebit);

            BigDecimal clrBalAmtCr = accountCredit.getClr_bal_amt().add(request.getAmount());
            BigDecimal cumCalCrAmtCr = accountCredit.getCum_cr_amt().add(request.getAmount());

            accountCredit.setClr_bal_amt(clrBalAmtCr);
            accountCredit.setCum_cr_amt(cumCalCrAmtCr);
            accountRepository.saveAndFlush(accountCredit);


            response = new BankServiceResponse("00", "payment completed successfully", sessionId);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return response;
    }
}
