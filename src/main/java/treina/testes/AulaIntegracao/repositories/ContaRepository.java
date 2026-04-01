package treina.testes.AulaIntegracao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import treina.testes.AulaIntegracao.model.Conta;
@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
