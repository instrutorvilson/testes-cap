package treina.testes.AulaIntegracao.unitarios;

import org.junit.jupiter.api.Test;
import treina.testes.AulaIntegracao.model.Conta;
import treina.testes.AulaIntegracao.service.ContaService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaServiceFakeTest {
    @Test
    void deveTransferirComSucessoUsandoFake(){
        ContaRepositoryFake fakeRepo = new ContaRepositoryFake();
        ContaService service = new ContaService(fakeRepo);

        Conta origem = new Conta("João", new BigDecimal("1000.00"));
        Conta destino = new Conta("Maria", new BigDecimal("500.00"));

        origem = fakeRepo.save(origem);
        destino = fakeRepo.save(destino);

        service.transferir(origem.getId(), destino.getId(), new BigDecimal("200.00"));

        Conta origemAtualizada = fakeRepo.findById(origem.getId()).get();
        Conta destinoAtualizada = fakeRepo.findById(destino.getId()).get();

        assertEquals(new BigDecimal("800.00"), origemAtualizada.getSaldo());
        assertEquals(new BigDecimal("700.00"), destinoAtualizada.getSaldo());
    }

}
