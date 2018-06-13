package br.com.munieri.pagamento.service;

import br.com.munieri.pagamento.controller.ContaDTO;
import br.com.munieri.pagamento.model.Conta;

public interface ContaService {

    Conta creditCardTransaction(ContaDTO dto);

}
