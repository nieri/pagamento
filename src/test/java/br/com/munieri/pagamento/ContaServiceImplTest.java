package br.com.munieri.pagamento;


import br.com.munieri.pagamento.controller.ContaDTO;
import br.com.munieri.pagamento.exception.ContaNaoEncontrada;
import br.com.munieri.pagamento.exception.NumeroCartaoInvalido;
import br.com.munieri.pagamento.exception.SaldoInsuficiente;
import br.com.munieri.pagamento.exception.TipoTransacaoInvalido;
import br.com.munieri.pagamento.model.Conta;
import br.com.munieri.pagamento.repository.ContaRepository;
import br.com.munieri.pagamento.service.ContaService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaServiceImplTest {

    @MockBean
    ContaRepository repository;

    @Autowired
    ContaService contaService;

    @Before
    public void setUp() {
        Conta conta = new Conta("12345678901234", 1000.00);
        Mockito.when(repository.findByCardNumber(conta.getCardNumber())).thenReturn(Optional.of(conta));
        Mockito.when(repository.save(conta)).thenReturn(conta);

    }

    @Test(expected = NumeroCartaoInvalido.class)
    public void deve_lancar_excessao_quando_numero_cartao_invalido() {
        String cardNumber = "123456789012";
        ContaDTO dto = new ContaDTO("withdraw", cardNumber, 1.00);
        contaService.creditCardTransaction(dto);
    }

    @Test(expected = TipoTransacaoInvalido.class)
    public void deve_lancar_excessao_quando_nao_encontrar_tipo_transacao() {
        String cardNumber = "12345678901234";
        ContaDTO dto = new ContaDTO("", cardNumber, 1.00);
        contaService.creditCardTransaction(dto);
    }

    @Test(expected = ContaNaoEncontrada.class)
    public void deve_lancar_excessao_quando_nao_encontrar_conta() {
        String cardNumber = "12345678901200";
        ContaDTO dto = new ContaDTO("withdraw", cardNumber, 1.00);
        contaService.creditCardTransaction(dto);
    }

    @Test(expected = SaldoInsuficiente.class)
    public void deve_validar_saldo_insuficiente() {
        String cardNumber = "12345678901234";
        ContaDTO dto = new ContaDTO("withdraw", cardNumber, 2000.00);
        contaService.creditCardTransaction(dto);
    }

    @Test
    public void deve_realizar_transacao() {
        String cardNumber = "12345678901234";
        ContaDTO dto = new ContaDTO("withdraw", cardNumber, 500.00);
        Conta conta = contaService.creditCardTransaction(dto);

        assertThat(conta.getAmount(), is(equalTo(500.00)));
        Assertions.assertThat(conta.getAuthorizationCode()).isNotNull();
    }
}
