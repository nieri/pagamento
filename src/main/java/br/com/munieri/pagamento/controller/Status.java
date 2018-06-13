package br.com.munieri.pagamento.controller;

public enum Status {

    APROVADO("00"),
    SALDO_INSUFICIENTE("51"),
    CONTA_INVALIDA("14"),
    ERRO_PROCESSAMENTO("96");

    private String codigo;

    Status(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
