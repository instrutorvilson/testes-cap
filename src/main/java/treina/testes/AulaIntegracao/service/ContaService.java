package treina.testes.AulaIntegracao.service;

import org.springframework.stereotype.Service;
import treina.testes.AulaIntegracao.model.Conta;
import treina.testes.AulaIntegracao.repositories.ContaRepository;

import java.math.BigDecimal;

@Service
public class ContaService {
    private final ContaRepository repository;
    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }
    public Conta salvar(Conta conta) {
        return repository.save(conta);
    }

    public void transferir(Long origemId, Long destinoId, BigDecimal
            valor) {
        Conta origem = repository.findById(origemId).orElseThrow();
        Conta destino = repository.findById(destinoId).orElseThrow();
        if (origem.getSaldo().compareTo(valor) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
        origem.setSaldo(origem.getSaldo().subtract(valor));
        destino.setSaldo(destino.getSaldo().add(valor));
        repository.save(origem);
        repository.save(destino);
    }

}
