package com.paymentgateway.gpay;

import com.paymentgateway.gpay.bankService.entity.Account;
import com.paymentgateway.gpay.bankService.entity.CardInfo;
import com.paymentgateway.gpay.bankService.entity.User;
import com.paymentgateway.gpay.bankService.enums.AccountType;
import com.paymentgateway.gpay.bankService.repository.AccountRepository;
import com.paymentgateway.gpay.bankService.repository.CardInfoRepository;
import com.paymentgateway.gpay.util.Helper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Component
public class SetupDataLoader {

    boolean alreadySetup = false;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardInfoRepository cardInfoRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("even" + event);
        if (alreadySetup)
            return;

        User user1 = new User(1L);
        User user2 = new User(2L);
        User user3 = new User(3L);
        User user4 = new User(4L);
        User user5 = new User(5L);

        // Merchant user
        User merchant = new User(6L);

        String accountNo1 = "0027996329";
        String accountNo2 = "0027996320";
        String accountNo3 = "0027996321";
        String accountNo4 = "0027996322";
        String accountNo5 = "0027996323";

        String merchantCreditAccount = "0027996328";

        String currencyCode = "NGN";
        // add Account
        String hashed_no1 = "";
        String hashed_no2 = "";
        String hashed_no3 = "";
        String hashed_no4 = "";
        String hashed_no5 = "";
        String hashed_no6 = "";
        try {
            hashed_no1 = Helper.hashEncrypt(user1.getId() + "|" + accountNo1 + "|" + currencyCode);
            hashed_no2 = Helper.hashEncrypt(user2.getId() + "|" + accountNo2 + "|" + currencyCode);
            hashed_no3 = Helper.hashEncrypt(user3.getId() + "|" + accountNo3 + "|" + currencyCode);
            hashed_no4 = Helper.hashEncrypt(user4.getId() + "|" + accountNo4 + "|" + currencyCode);
            hashed_no5 = Helper.hashEncrypt(user5.getId() + "|" + accountNo5 + "|" + currencyCode);
            hashed_no6 = Helper.hashEncrypt(merchant.getId() + "|" + merchantCreditAccount + "|" + currencyCode);

        }catch (Exception ex){
            ex.printStackTrace();
        }


        Account account1 = new Account(new BigDecimal("5000.00"), new BigDecimal("5000.00"), false, "0000", accountNo1, "DAVID MARK", "0", "D", "", false, new BigDecimal("5000.00"), new BigDecimal("5000.00"), hashed_no1, new BigDecimal("0.00"), new BigDecimal("5000.00"), new BigDecimal("5000.00"), "", AccountType.SAVINGS.name(), "tst@gmail.com", currencyCode, user1, new Date());
        accountRepository.save(account1);
        Account account2 = new Account(new BigDecimal("5000.00"), new BigDecimal("5000.00"), false, "0000", accountNo2, "LUNEN MARK", "0", "D", "", false, new BigDecimal("5000.00"), new BigDecimal("5000.00"), hashed_no2, new BigDecimal("0.00"), new BigDecimal("5000.00"), new BigDecimal("5000.00"), "", AccountType.SAVINGS.name(), "lunen@gmail.com", currencyCode, user2, new Date());
        accountRepository.save(account2);
        Account account3 = new Account(new BigDecimal("5000.00"), new BigDecimal("5000.00"), false, "0000", accountNo3, "LINDA MARK", "0", "D", "", false, new BigDecimal("5000.00"), new BigDecimal("5000.00"), hashed_no3, new BigDecimal("0.00"), new BigDecimal("5000.00"), new BigDecimal("5000.00"), "", AccountType.SAVINGS.name(), "linda@gmail.com", currencyCode, user3, new Date());
        accountRepository.save(account3);
        Account account4 = new Account(new BigDecimal("5000.00"), new BigDecimal("5000.00"), false, "0000", accountNo4, "QUEEN MARK", "0", "D", "", false, new BigDecimal("5000.00"), new BigDecimal("5000.00"), hashed_no4, new BigDecimal("0.00"), new BigDecimal("5000.00"), new BigDecimal("5000.00"), "", AccountType.SAVINGS.name(), "queen@gmail.com", currencyCode, user4, new Date());
        accountRepository.save(account4);
        Account account5 = new Account(new BigDecimal("5000.00"), new BigDecimal("5000.00"), false, "0000", accountNo5, "LOVETH MARK", "0", "D", "", false, new BigDecimal("5000.00"), new BigDecimal("5000.00"), hashed_no5, new BigDecimal("0.00"), new BigDecimal("5000.00"), new BigDecimal("5000.00"), "", AccountType.SAVINGS.name(), "loveth@gmail.com", currencyCode, user5, new Date());
        accountRepository.save(account5);

        Account account6 = new Account(new BigDecimal("5000.00"), new BigDecimal("5000.00"), false, "0000", merchantCreditAccount, "Merchant Account", "0", "D", "", false, new BigDecimal("5000.00"), new BigDecimal("5000.00"), hashed_no6, new BigDecimal("0.00"), new BigDecimal("5000.00"), new BigDecimal("5000.00"), "", AccountType.SAVINGS.name(), "loveth@gmail.com", currencyCode, user5, new Date());
        accountRepository.save(account6);


        CardInfo cardInfo1 = new CardInfo(accountNo1, "DAVID MARK", Helper.generateMastercardNumber(), "07/24", 190);
        log.info("getAccountNo => " + cardInfo1.getAccountNo());
        log.info("getCardName => " + cardInfo1.getCardName());
        log.info("getCardNumber => " + cardInfo1.getCardNumber());
        log.info("getExpiryDate => " + cardInfo1.getExpiryDate());
        log.info("getCvv => " + cardInfo1.getCvv());
        cardInfoRepository.save(cardInfo1);

        CardInfo cardInfo2 = new CardInfo(accountNo2, "LUNEN MARK", Helper.generateMastercardNumber(),"08/24", 180);
        cardInfoRepository.save(cardInfo2);

        CardInfo cardInfo3 = new CardInfo(accountNo3, "LINDA MARK", Helper.generateMastercardNumber(),"09/24", 390);
        cardInfoRepository.save(cardInfo3);

        CardInfo cardInfo4 = new CardInfo(accountNo4, "QUEEN MARK", Helper.generateMastercardNumber(),"10/24", 590);
        cardInfoRepository.save(cardInfo4);

        CardInfo cardInfo5 = new CardInfo(accountNo5, "LOVETH MARK", Helper.generateMastercardNumber(),"11/24", 690);
        cardInfoRepository.save(cardInfo5);


        //Setup Internal account CFID
//        userAccountService.setupSystemUser();

//        List<WalletEventCharges> eventchg = walletEventRepo.findAll();
//        if (eventchg != null) {
//            for (WalletEventCharges mEvent : eventchg) {
//                if (!mEvent.isProcessflg()) {
//                    WalletEventAccountDTO account = new WalletEventAccountDTO(mEvent.getPlaceholder(),
//                            mEvent.getCrncyCode(), "OABAS", mEvent.getTranNarration(), "11104",mEvent.getEventId(), "SAVINGS", "SAVINGS ACCOUNT");
//                    userAccountService.createEventAccount(account);
//                }
//            }
//        }

        //userAccountService.setupExternalCBA();

        alreadySetup = true;

    }
}
