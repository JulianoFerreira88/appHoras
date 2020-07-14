package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConnection(String banco) throws SQLException {
        return DriverManager.getConnection(banco);
    }
}
