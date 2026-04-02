package treina.testes.TesteApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import treina.testes.TesteApi.model.Conta;

@Repository
public interface ContaRespository extends JpaRepository<Conta, Long> {
}
