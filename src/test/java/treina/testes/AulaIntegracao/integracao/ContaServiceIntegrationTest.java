package treina.testes.AulaIntegracao.integracao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import treina.testes.AulaIntegracao.model.Conta;
import treina.testes.AulaIntegracao.repositories.ContaRepository;
import treina.testes.AulaIntegracao.service.ContaService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ContaServiceIntegrationTest {
    @Autowired
    private ContaService service;
    @Autowired
    private ContaRepository repository;

    @Test
    public void deveSalvarConta() {
        Conta conta = new Conta("João", new BigDecimal("1000.00"));
        Conta salva = service.salvar(conta);
        assertNotNull(salva.getId());
    }
    @Test
    public void deveTransferirEntreContas() {
        Conta c1 = repository.save(new Conta("João", new BigDecimal("1000.0")));
        Conta c2 = repository.save(new Conta("Maria", new BigDecimal("500.0")));
        service.transferir(c1.getId(), c2.getId(), new BigDecimal("200.0"));
        Conta origem = repository.findById(c1.getId()).get();
        Conta destino = repository.findById(c2.getId()).get();

        assertEquals(0, origem.getSaldo().compareTo(new BigDecimal("800.00")));
        assertEquals(0, destino.getSaldo().compareTo(new BigDecimal("700.00")));
    }

    @Test
    public void naoDeveTransferirSaldoInsuficiente() {
        Conta c1 = repository.save(new Conta("João", new BigDecimal("100.00")));
        Conta c2 = repository.save(new Conta("Maria", new BigDecimal("500.00")));

        assertThrows(RuntimeException.class, () -> {
            service.transferir(
                    c1.getId(),
                    c2.getId(),
                    new BigDecimal("200.00")
            );
        });
    }
}
