package org.scoula.pokemon.dao;

import org.scoula.pokemon.common.JDBCUtil;
import org.scoula.pokemon.vo.CapturedPokemonVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CapturedPokemonDaoImp implements CapturedPokemonDao{
    private Connection conn = JDBCUtil.getConnection();

    @Override
    public int insert(CapturedPokemonVO myPokemon) throws SQLException {
        String sql = "insert into capturedPokemon (pokemonId, name) value (?, ?)";
        int count = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, myPokemon.getPokemonId());
            pstmt.setString(2, myPokemon.getName());

            count = pstmt.executeUpdate();
        }

        return count;
    }

    @Override
    public List<CapturedPokemonVO> list() throws SQLException {
        String sql = "select * from capturedPokemon";
        List<CapturedPokemonVO> list = new ArrayList<>();

        try( Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                CapturedPokemonVO myPokemon = new CapturedPokemonVO();

                myPokemon.setId(rs.getInt("id"));
                myPokemon.setPokemonId(rs.getInt("pokemonId"));
                myPokemon.setName(rs.getString("name"));

                list.add(myPokemon);
            }
        }

        return list;
    }

    @Override
    public CapturedPokemonVO get(int id) throws SQLException {
        String sql = "select * from capturedPokemon where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                CapturedPokemonVO myPokemon = new CapturedPokemonVO();

                myPokemon.setId(rs.getInt("id"));
                myPokemon.setPokemonId(rs.getInt("pokemonId"));
                myPokemon.setName(rs.getString("name"));

                return myPokemon;
            }
        }

        return null;
    }

    @Override
    public int updateName(CapturedPokemonVO myPokemon) throws SQLException {
        String name = "updatedName";
        String sql = "update capturedPokemon set name = ? where id = ?";
        int count = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, myPokemon.getName());
            pstmt.setInt(2, myPokemon.getId());

            count = pstmt.executeUpdate();
        }

        return count;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "delete from capturedPokemon where id = ?";
        int count = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            count = pstmt.executeUpdate();
        }

        return count;
    }
}
