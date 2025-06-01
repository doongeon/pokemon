package org.scoula.pokemon.test.crud;


import org.junit.jupiter.api.*;
import org.scoula.pokemon.common.JDBCUtil;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Pokemon {
    private static Connection conn = JDBCUtil.getConnection();

    @Test
    @DisplayName("insert pokemon")
    @Order(1)
    public void insertPokemon() throws SQLException {
        String sql = "insert into pokemon (name, type, otherType, hp, attack, defense) value (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "test");
            pstmt.setString(2, "testType");
            pstmt.setString(3, "testOtherType");
            pstmt.setInt(4, 1);
            pstmt.setInt(5, 1);
            pstmt.setInt(6, 1);

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }
    }

    @Test
    @DisplayName("select pokemons")
    @Order(2)
    public void selectPokemons() throws SQLException{
        String sql = "select * from pokemon";

        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
    }

    @Test
    @DisplayName("select pokemon by id")
    @Order(3)
    public void selectPokemonById() throws SQLException {
        int id = 4;
        String sql = "select * from pokemon where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
    }

    @Test
    @DisplayName("update pokemon")
    @Order(4)
    public void updatePokemon() throws SQLException {
        int id = 4;
        String name = "testUpdate";
        String sql = "update pokemon set name = ? where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }
    }

    @Test
    @DisplayName("delete pokemon")
    @Order(5)
    public void deletePokemon() throws SQLException {
        int id = 4;
        String sql = "delete from pokemon where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }
    }


    @AfterAll
    public static void tearDown() {
        JDBCUtil.close();
    }

}
