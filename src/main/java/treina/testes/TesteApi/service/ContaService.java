package treina.testes.TesteApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import treina.testes.TesteApi.model.Conta;
import treina.testes.TesteApi.repository.ContaRespository;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRespository repository;

    public Conta salvar(Conta conta) {
        return repository.save(conta);
    }

    public List<Conta> listar() {
        return repository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public Conta atualizar(Long id, Conta contaAtualizada) {
        Conta conta = buscarPorId(id);
        conta.setTitular(contaAtualizada.getTitular());
        conta.setSaldo(contaAtualizada.getSaldo());
        return repository.save(conta);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
