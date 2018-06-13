package br.com.munieri.pagamento.service;

import br.com.munieri.pagamento.controller.ContaDTO;
import br.com.munieri.pagamento.exception.ContaNaoEncontrada;
import br.com.munieri.pagamento.exception.SaldoInsuficiente;
import br.com.munieri.pagamento.exception.TransacaoValidator;
import br.com.munieri.pagamento.model.Conta;
import br.com.munieri.pagamento.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    ContaRepository repository;

    @Autowired
    TransacaoValidator validator;

    @Override
    public Conta creditCardTransaction(ContaDTO dto) {

        validator.validate(dto);

        Conta conta = this.buscaEVerificaConta(dto);
        String codigoAutorizacao = this.geraCodigoAutorizacao();

        conta.setAmount(conta.getAmount() - dto.getAmount());
        conta.setAuthorizationCode(codigoAutorizacao);
        Conta contaAtualizada = repository.save(conta);

        return contaAtualizada;
    }

    private String geraCodigoAutorizacao() {
        return "" + ThreadLocalRandom.current().nextInt(1, 999999);
    }

    private Conta buscaEVerificaConta(ContaDTO dto) {

        Optional<Conta> conta = repository.findByCardNumber(dto.getCardNumber());
        if (!conta.isPresent()) {
            throw new ContaNaoEncontrada();
        }

        this.verificaSaldo(conta.get(), dto);
        return conta.get();
    }

    private boolean verificaSaldo(Conta conta, ContaDTO dto) {

        if (conta.getAmount() >= dto.getAmount()) {
            return true;
        }
        throw new SaldoInsuficiente();
    }
}
