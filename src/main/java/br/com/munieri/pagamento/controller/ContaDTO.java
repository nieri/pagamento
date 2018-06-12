package br.com.munieri.pagamento.controller;

public class ContaDTO {

    private String action;

    private String code;

    private String authorizationCode;

    private String cardNumber;

    private Double amount;

    public ContaDTO() {
    }

    public ContaDTO(String action, String cardNumber, Double amount) {
        this.action = action;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
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
}
