package model.entities.dao;

import conexao.Conexao;
import model.entities.Registro;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistroDao {


    public static void insert(Connection con, Registro registro) throws SQLException {
        String sql = "INSERT INTO Registro () VALUES ()";
        Statement st = con.createStatement();
        st.execute(sql);
    }
}
