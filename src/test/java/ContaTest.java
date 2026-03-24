import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import treina.testes.Conta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaTest {
    private Conta conta;
    private double valorDeposito;

    @BeforeEach
    void setup(){
        conta = new Conta();
        valorDeposito = 100;
    }
    @Test
    void deveDepositarValorValido(){
        conta.depositar(valorDeposito);
        assertEquals(100,conta.getSaldo());
    }

    @Test
    void lancaExcecaoAoDepositarValorNegativo(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->{
            conta.depositar(-10);
        });
        assertEquals("Valor de depósito inválido",ex.getMessage());
    }

    @Test
    void deveSacarValorValido(){
        conta.depositar(valorDeposito);
        conta.sacar(50);
        assertEquals(50,conta.getSaldo());
    }

    @Test
    void lancaExcecaoAoSacarValorNegativo(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->{
            conta.sacar(-10);
        });
        assertEquals("Valor de saque inválido",ex.getMessage());
    }

    @Test
    void lancaExcecaoAoSacarSaldoInsuficiente(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->{
            conta.sacar(50);
        });
        assertEquals("Saldo insuficiente",ex.getMessage());
    }
}
