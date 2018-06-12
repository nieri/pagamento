package br.com.munieri.pagamento.service;

import br.com.munieri.pagamento.model.Conta;
import br.com.munieri.pagamento.controller.ContaDTO;
import br.com.munieri.pagamento.exception.ContaNaoEncontrada;
import br.com.munieri.pagamento.exception.SaldoInsuficiente;

public interface ContaService {

    Conta creditCardTransaction(ContaDTO dto) throws ContaNaoEncontrada, SaldoInsuficiente;

}
