package treina.testes.AulaIntegracao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import treina.testes.AulaIntegracao.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
