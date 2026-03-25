package treina.aulas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
   private Calculadora calc;

   @BeforeEach
   void setup(){
      calc = new Calculadora();
   }

   @Test
   void deveSomarCorretamente(){
        //Act
        int atual = calc.somar(3, 2);
        //Assert
        assertEquals(5,atual);
   }

   @Test
   void deveSubtrairCorretamente(){
        //Act
        int atual = calc.subtrair(3, 2);
        //Assert
        assertEquals(1,atual);
   }

   @Test
   void deveMulitplicarCorretamente(){   
        int atual = calc.multiplicar(3, 2);     
        assertEquals(6,atual);
   }

   @Test
   void deveDividirCorretamente(){   
        double atual = calc.dividir(10, 2);     
        assertEquals(5,atual);
   }

    @Test
   void deveLancarExcecaoAoDividirporZero(){   
        assertThrows(RuntimeException.class, ()->{
          calc.dividir(10, 0);
        });
   }
}
