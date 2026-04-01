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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    void deveTransferirComSucesso(){
        Conta origem = new Conta(1L,"Joao",new BigDecimal("1000.00"));
        Conta destino = new Conta(2L,"Maria",new BigDecimal("500.00"));

        //stubs
        when(repository.findById(1L)).thenReturn(Optional.of(origem));
        when(repository.findById(2L)).thenReturn(Optional.of(destino));

        service.transferir(1L,2L, new BigDecimal("200.00"));

        assertEquals(new BigDecimal("800.00"), origem.getSaldo());
        assertEquals(new BigDecimal("700.00"), destino.getSaldo());

        verify(repository).save(origem);
        verify(repository).save(destino);
    }

    @Test
    void lancarExcecaoAoTransferirComSaldoInsuficiente(){
        Conta origem = new Conta(1L,"Joao",new BigDecimal("100.00"));
        Conta destino = new Conta(2L,"Maria",new BigDecimal("500.00"));

        //stubs
        when(repository.findById(1L)).thenReturn(Optional.of(origem));
        when(repository.findById(2L)).thenReturn(Optional.of(destino));

        assertThrows(RuntimeException.class, () -> {
           service.transferir(1L,2l, new BigDecimal("200.00"));
        });

        verify(repository,never()).save(any());
    }
}
