package org.scoula.pokemon.test.crud;

import org.junit.jupiter.api.*;
import org.scoula.pokemon.common.JDBCUtil;

import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CapturedPokemon {
    private Connection conn = JDBCUtil.getConnection();
    private final int POKEMON_ID = 806;

    @Test
    @DisplayName("insert captured pokemon")
    @Order(1)
    public void insertPokemon() throws SQLException {
        String sql = "insert into capturedPokemon (pokemonId, name) value (?, ?)";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, POKEMON_ID);
            pstmt.setString(2, "test");

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }
    }

    @Test
    @DisplayName("select captured pokemons")
    @Order(2)
    public void selectCapturedPokemons() throws SQLException {
        String sql = "select * from capturedPokemon";

        try( Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
            }
        }
    }

    @Test
    @DisplayName("select captured pokemon by id")
    @Order(3)
    public void getCapturedPokemonById() throws SQLException {
        int id = 1;
        String sql = "select * from capturedPokemon where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("name"));
            }
        }
    }

    @Test
    @DisplayName("update name of captured pokemon")
    @Order(4)
    public void updateCapturedPokemonName() throws SQLException {
        int id = 2;
        String name = "updatedName";
        String sql = "update capturedPokemon set name = ? where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            int count = pstmt.executeUpdate();
            Assertions.assertEquals(1, count);
        }

    }

    @Test
    @DisplayName("delete captured pokemon")
    @Order(5)
    public void deleteCapturedPokemon() throws SQLException {
        int id = 2;
        String sql = "delete from capturedPokemon where id = ?";

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
