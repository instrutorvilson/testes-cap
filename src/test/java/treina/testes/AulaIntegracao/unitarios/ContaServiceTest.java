package treina.testes.AulaIntegracao.unitarios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import treina.testes.AulaIntegracao.model.Conta;
import treina.testes.AulaIntegracao.repositories.ContaRepository;
import treina.testes.AulaIntegracao.service.ContaService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {
    @Mock
    private ContaRepository repository;
    @InjectMocks
    private ContaService service;
    @Test
    void deveSalvarConta(){
        Conta conta = new Conta("Joao",new BigDecimal("1000.00"));
        //stub
        when(repository.save(conta)).thenReturn(conta);
        Conta resultado = service.salvar(conta);
        assertNotNull(resultado);
        assertEquals("Joao",resultado.getTitular());
        //mock
        verify(repository,times(1)).save(conta);
    }
}
