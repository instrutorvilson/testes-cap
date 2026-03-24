import org.junit.jupiter.api.BeforeEach;
import treina.testes.Calculadora;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraTest {
    private Calculadora calc;

    @BeforeEach
    void setup(){
        calc = new Calculadora();
    }

    @Test
    void deveSomarCorretamente(){
        int atual = calc.somar(5,2);
        assertEquals(7, atual);
    }

    @Test
    void deveSubtrairCorretamente(){
        int retorno = calc.subtrair(5,2);
        assertEquals(3, retorno);
    }

    @Test
    void deveMultiplicarCorretamente(){
        int atual = calc.multiplicar(5,2);
        assertEquals(10, atual);
    }

    @Test
    void deveDividirCorretamente(){
        float atual = calc.dividir(10,2);
        assertEquals(5, atual);
    }

    @Test
    void deveLancarExcecaoQuandoDividirPorZero(){
        assertThrows(RuntimeException.class, ()->{
            calc.dividir(10,0);
        });
    }

    @Test
    void deveLancarExcecaoRuntimeQuandoDividirPorZero(){
        Exception ex = assertThrows(RuntimeException.class,() -> {
            calc.dividir(10,0);
        });
        assertEquals("Não é divisivel por zero", ex.getMessage());
    }

}
