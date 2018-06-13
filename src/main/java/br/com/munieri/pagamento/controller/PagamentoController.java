package br.com.munieri.pagamento.controller;

import br.com.munieri.pagamento.exception.*;
import br.com.munieri.pagamento.model.Conta;
import br.com.munieri.pagamento.service.ContaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    ContaService service;

    private ContaDTO contaDTO;
    private Conta conta;

    @MessageMapping("/transacoes")
    @SendTo("/topic/transacoes")
    public ContaDTO post(ContaDTO dto) throws Exception {
        contaDTO = dto;
        Thread.sleep(1000); // simulated delay

        conta = service.creditCardTransaction(dto);

        return new ContaDTO(dto.getAction(), Status.APROVADO.getCodigo(), conta.getAuthorizationCode());
    }

    @MessageExceptionHandler({NumeroCartaoInvalido.class, TipoTransacaoInvalido.class, ValorInvalido.class, MessageConversionException.class})
    public ContaDTO errorHandler(Exception ex) {
        logger.error(ex);
        return new ContaDTO(contaDTO.getAction(), Status.ERRO_PROCESSAMENTO.getCodigo());
    }

    @MessageExceptionHandler(SaldoInsuficiente.class)
    public ContaDTO errorHandler(SaldoInsuficiente ex) {
        logger.error(ex);
        return new ContaDTO(contaDTO.getAction(), Status.SALDO_INSUFICIENTE.getCodigo());
    }

    @MessageExceptionHandler(ContaNaoEncontrada.class)
    public ContaDTO errorHandler(ContaNaoEncontrada ex) {
        logger.error(ex);
        return new ContaDTO(contaDTO.getAction(), Status.CONTA_INVALIDA.getCodigo());
    }
}
