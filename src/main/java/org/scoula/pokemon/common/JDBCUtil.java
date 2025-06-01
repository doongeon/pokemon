package org.scoula.pokemon.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static Connection conn = null;

    public static Connection getConnection() {
        if(conn != null) return conn;

        try {
            Properties props = new Properties();
            props.load(JDBCUtil.class.getResourceAsStream("/application.properties"));
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String id = props.getProperty("id");
            String pw = props.getProperty("pw");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void close() {
        try {
            if(conn != null) {
                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}