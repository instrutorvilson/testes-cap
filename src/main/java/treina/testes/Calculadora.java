package treina.testes;

public class Calculadora {
    public int somar(int a, int b){
        return a + b;
    }
    public int subtrair(int a, int b){
        return a - b;
    }

    public int multiplicar(int a, int b){
        return a * b;
    }

    public float dividir(int a, int b){
        if(b == 0){
            throw  new RuntimeException("Não é divisivel por zero");
        }
        return a/b;
    }
}
