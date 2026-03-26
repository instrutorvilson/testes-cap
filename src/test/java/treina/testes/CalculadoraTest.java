package treina.testes;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    static Stream<Arguments> dadosSoma() {
        return Stream.of(
                Arguments.of(2, 3, 5),
                Arguments.of(4, 6, 10),
                Arguments.of(1, 1, 2),
                Arguments.of(2,2,4)
        );
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void deveTestarNumerosPositivos(int numero) {
        assertTrue(numero > 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void deveLancarExcecaoQuandoDivisorInvalido(int divisor) {
        Calculadora calc = new Calculadora();
        assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(10, divisor);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "2,3,5",
            "10,5,15",
            "0,0,0",
            "2,2,4"
    })
    void deveSomarCorretamente(int a, int b, int resultado) {
        Calculadora calc = new Calculadora();
        assertEquals(resultado, calc.somar(a, b));
    }

    @ParameterizedTest
    @MethodSource("dadosSoma")
    void deveSomarComMethodSource(int a, int b, int resultado) {
        Calculadora calc = new Calculadora();
        assertEquals(resultado, calc.somar(a, b));
    }
}