package br.com.munieri.pagamento;


import br.com.munieri.pagamento.model.Conta;
import br.com.munieri.pagamento.repository.ContaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContaRepositoryTest {

    @Autowired
    ContaRepository repository;

    Conta conta1, conta2;

    @Before
    public void setUp() {
        repository.deleteAll();
        conta1 = repository.save(new Conta("12345678901234", 1000.00));
        conta2 = repository.save(new Conta("12345678901234", 1000.00));
    }

    @Test
    public void deve_buscar_pelo_numero_do_cartao() {
        String cardNumber = "12345678901234";
        Optional<Conta> conta = repository.findByCardNumber(cardNumber);

        assertThat(conta.get().getCardNumber()).isEqualTo(cardNumber);
    }
}
