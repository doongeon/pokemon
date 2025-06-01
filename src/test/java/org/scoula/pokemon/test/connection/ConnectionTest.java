package org.scoula.pokemon.test.connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.scoula.pokemon.common.JDBCUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @Test
    @DisplayName("mysql connection test")
    public void testConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/pokemon";
        String id = "scoula";
        String pw = "1234";

        Connection conn = DriverManager.getConnection(url, id, pw);
        conn.close();
    }

    @Test
    @DisplayName("JDBCUtil test")
    public void testJDBCUtil() throws SQLException, ClassNotFoundException{
        try(Connection conn = JDBCUtil.getConnection()) {
        }
    }
}
