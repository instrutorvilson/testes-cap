package treina.testes;

public class Calculadora {
    public int somar(int a, int b){
        return a + b;
    }

    public double dividir(int a, int b){
        if(b == 0){
            throw new IllegalArgumentException("Divisão por zero");
        }
        if(b < 0){
            throw new IllegalArgumentException("Divisão por numero negativo");
        }

        return a/b;
    }

}
