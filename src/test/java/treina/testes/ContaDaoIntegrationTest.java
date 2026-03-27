package treina.testes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContaDaoIntegrationTest {
    @Test
    public void deveInserirContaNoBanco() throws Exception {

        ContaDao dao = new ContaDao();

        dao.criarTabela(); // prepara banco

        Conta conta = new Conta("João", 1000.0);

        dao.salvar(conta);
        int total = dao.contarRegistros();
        assertEquals(1, total);
    }

    @Test
    public void deveTransferirValorEntre() throws Exception{
        ContaDao contaDao = new ContaDao();
        contaDao.criarTabela();
        Conta origem = new Conta("João", 1000.0);
        Conta destino = new Conta("Maria", 500.0);
        contaDao.salvar(origem);
        contaDao.salvar(destino);

        contaDao.transferir(1,2,200.0);

        //verificar saldos
        double saldoOrigem = contaDao.buscarSaldo(1);
        double saldoDestino = contaDao.buscarSaldo(2);

        //assertions
        assertEquals(800.0,saldoOrigem);
        assertEquals(700.0,saldoDestino);
    }


}
