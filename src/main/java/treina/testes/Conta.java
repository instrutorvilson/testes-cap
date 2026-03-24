package treina.testes;

public class Conta {
        private double saldo;

        public Conta() {
            this.saldo = 0;
        }

        public double getSaldo() {
            return saldo;
        }

        public void depositar(double valor) {
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor de depósito inválido");
            }
            saldo += valor;
        }

        public void sacar(double valor) {
            if (valor <= 0) {
                throw new IllegalArgumentException("Valor de saque inválido");
            }

            if (valor > saldo) {
                throw new IllegalArgumentException("Saldo insuficiente");
            }

            saldo -= valor;
        }
    }


