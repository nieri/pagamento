package br.com.munieri.pagamento.initializer;

import br.com.munieri.pagamento.model.Conta;
import br.com.munieri.pagamento.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {


    @Autowired
    ContaRepository repository;

    @PostConstruct
    public void init() {

        repository.save(new Conta("1234567890123456", 1000.00));

    }
}
