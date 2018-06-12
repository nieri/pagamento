package br.com.munieri.pagamento.repository;

import br.com.munieri.pagamento.model.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContaRepository extends MongoRepository<Conta, String> {

    Optional<Conta> findByCardNumber(String cardNumber);
}
