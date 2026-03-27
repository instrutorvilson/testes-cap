package treina.testes;

import treina.testes.utils.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ContaDao {
    public void criarTabela() throws Exception {
        Connection conn = Conexao.getConnection();
        Statement st = conn.createStatement();

        st.execute("CREATE TABLE conta (id INT AUTO_INCREMENT PRIMARY KEY, titular VARCHAR(100), saldo DOUBLE)");
        conn.close();
    }
    public void salvar(Conta conta) throws Exception {
        Connection conn = Conexao.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO conta (titular, saldo) VALUES (?, ?)"
        );

        ps.setString(1, conta.getTitular());
        ps.setDouble(2, conta.getSaldo());

        ps.executeUpdate();
        conn.close();
    }
    public int contarRegistros() throws Exception {
        Connection conn = Conexao.getConnection();
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM conta");
        rs.next();

        int total = rs.getInt(1);
        conn.close();

        return total;
    }

    public void transferir(int origem, int destino, double valor) throws  Exception{
        Connection conn = Conexao.getConnection();
        String sqlOrigem = "update conta set saldo = saldo - ? where id = ? ";
        String sqlDestino = "update conta set saldo = saldo + ? where id = ? ";

        //retira da origem
        PreparedStatement ps1 = conn.prepareStatement(sqlOrigem);
        ps1.setDouble(1, valor);
        ps1.setInt(2, origem);
        ps1.executeUpdate();

        //cretida no destino
        PreparedStatement ps2 = conn.prepareStatement(sqlDestino);
        ps2.setDouble(1, valor);
        ps2.setInt(2, destino);
        ps2.executeUpdate();
    }

    public double buscarSaldo(int conta) throws Exception {
        Connection conn = Conexao.getConnection();
        PreparedStatement ps1 = conn.prepareStatement("select saldo from conta where id = ?");
        ps1.setInt(1,conta);

        ResultSet rs = ps1.executeQuery();
        if(rs.next()){
            return  rs.getDouble("saldo");
        }
        return 0;
    }
}
