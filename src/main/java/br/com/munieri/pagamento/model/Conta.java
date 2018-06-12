package br.com.munieri.pagamento.model;

import org.springframework.data.annotation.Id;

public class Conta {

    @Id
    private String cardNumber;

    private Double amount;

    private String authorizationCode;

    public Conta() {
    }

    public Conta(String cardNumber, Double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
}
