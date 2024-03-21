package com.paymentgateway.gpay.bankService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paymentgateway.gpay.bankService.enums.AccountType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Data
@Entity
@Table(name = "bank_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_id_seq")
    @SequenceGenerator(name = "acc_id_seq", sequenceName = "acc_id_seq", allocationSize = 1)
    private Long id;

    private boolean del_flg;

    private String sol_id;

    @Column(unique = true, nullable = false, length = 10)
    private String accountNo;

    @Column(nullable = false)
    private String acct_name;

    private String acct_ownership;

    private String frez_code;
    private String frez_reason_code;

    @Column(nullable = false)
    private boolean acct_cls_flg;

    private BigDecimal clr_bal_amt;
    private BigDecimal un_clr_bal_amt;

    private BigDecimal cum_dr_amt;

    private BigDecimal cum_cr_amt;


    @Column(nullable = false)
    private String hashed_no;

    private BigDecimal lien_amt;

    private BigDecimal cash_dr_limit;

    private BigDecimal cash_cr_limit;

    private String lien_reason;

    private String accountType;

    private String notify_email;

    @Column(nullable = false)
    private String acct_crncy_code;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Account(BigDecimal cum_cr_amt, BigDecimal cum_dr_amt, boolean del_flg, String sol_id, String accountNo, String acct_name, String acct_ownership, String frez_code, String frez_reason_code, boolean acct_cls_flg, BigDecimal clr_bal_amt, BigDecimal un_clr_bal_amt, String hashed_no, BigDecimal lien_amt, BigDecimal cash_dr_limit, BigDecimal cash_cr_limit, String lien_reason, String accountType, String notify_email, String  acct_crncy_code, User user, Date createdAt) {

        this.cum_cr_amt = cum_cr_amt;
        this.cum_dr_amt = cum_dr_amt;
        this.del_flg = del_flg;
        this.sol_id = sol_id;
        this.accountNo = accountNo;
        this.acct_name = acct_name;
        this.acct_ownership = acct_ownership;
        this.frez_code = frez_code;
        this.frez_reason_code = frez_reason_code;
        this.acct_cls_flg = acct_cls_flg;
        this.clr_bal_amt = clr_bal_amt;
        this.un_clr_bal_amt = un_clr_bal_amt;
        this.hashed_no = hashed_no;
        this.lien_amt = lien_amt;

        this.cash_dr_limit = cash_dr_limit;
        this.cash_cr_limit = cash_cr_limit;
        this.lien_reason = lien_reason;
        this.accountType = accountType;
        this.notify_email = notify_email;
        this.acct_crncy_code = acct_crncy_code;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Account() {

    }
}
