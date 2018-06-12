package br.com.munieri.pagamento.exception;

import br.com.munieri.pagamento.controller.ContaDTO;
import org.springframework.stereotype.Component;

@Component
public class TransacaoValidator {

    public boolean validate(ContaDTO dto) {

        this.validaTipoTransacao(dto);
        this.validaNumeroCartao(dto);
        this.validaValorTransacao(dto);

        return true;
    }

    private boolean validaTipoTransacao(ContaDTO dto) {

        if (dto.getAction() != null && !"".equals(dto.getAction())) {
            return true;
        }
        throw new TipoTransacaoInvalido();
    }

    private boolean validaValorTransacao(ContaDTO dto) {

        if (dto.getAmount() != null && dto.getAmount() > 0) {
            return true;
        }
        throw new ValorInvalido();
    }

    private boolean validaNumeroCartao(ContaDTO dto) throws NumeroCartaoInvalido {
        if (dto.getCardNumber() != null && dto.getCardNumber().length() == 14) {
            return true;
        }
        throw new NumeroCartaoInvalido();
    }
}
