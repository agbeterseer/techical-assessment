package com.paymentgateway.gpay.bankService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "bank_card_infos")
public class CardInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_tran_id_seq")
    @SequenceGenerator(name = "card_id_seq", sequenceName = "card_tran_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNo;
    private String cardName;
    @Column(unique = true, nullable = false)
    private String cardNumber;
    private String expiryDate;

    @Column(scale = 3)
    private int cvv;

    public CardInfo(String accountNo, String cardName, String cardNumber, String expiryDate, int cvv) {
        this.accountNo = accountNo;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    public CardInfo() {
    }


    public String getFirst6(){

        return this.getCardNumber().substring(0, 6);
    }

    /**
     *
     * @return last 4 digits of card.
     */
    public String getLast4(){

        return this.getCardNumber().substring(this.getCardNumber().length() - 4, this.getCardNumber().length());
    }

    public String getMaskedCard(){

        StringBuilder maskedCard = new StringBuilder();
        maskedCard.append(this.getFirst6());

        for (int i = 0; i < this.getCardNumber().length()-10; i++) {
            maskedCard.append("*");
        }

        maskedCard.append(this.getLast4());

        return maskedCard.toString();

    }
}
