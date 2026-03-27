package treina.testes.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static Connection conn;

    public static Connection getConnection() throws Exception {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(
                    "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", ""
            );
        }
        return conn;
    }

}
