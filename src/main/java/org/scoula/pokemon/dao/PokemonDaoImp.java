package org.scoula.pokemon.dao;

import org.scoula.pokemon.common.JDBCUtil;
import org.scoula.pokemon.vo.PokemonVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonDaoImp implements PokemonDao{
    private Connection conn = JDBCUtil.getConnection();

    @Override
    public int create(PokemonVO pokemon) throws SQLException {
        String sql = "insert into pokemon (name, type, otherType, hp, attack, defense) value (?, ?, ?, ?, ?, ?)";
        int count = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pokemon.getName());
            pstmt.setString(2, pokemon.getType());
            pstmt.setString(3, pokemon.getOtherType());
            pstmt.setInt(4, pokemon.getHp());
            pstmt.setInt(5, pokemon.getAttack());
            pstmt.setInt(6, pokemon.getDefense());

            count = pstmt.executeUpdate();
        }

        return count;
    }

    @Override
    public List<PokemonVO> getList() throws SQLException {
        String sql = "select * from pokemon";
        List<PokemonVO> list = new ArrayList<>();

        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                PokemonVO pokemon = new PokemonVO();

                pokemon.setId(rs.getInt("id"));
                pokemon.setName(rs.getString("name"));
                pokemon.setType(rs.getString("type"));
                pokemon.setOtherType(rs.getString("otherType"));
                pokemon.setHp(rs.getInt("hp"));
                pokemon.setAttack(rs.getInt("attack"));
                pokemon.setDefense(rs.getInt("defense"));

                list.add(pokemon);
            }
        }

        return list;
    }

    @Override
    public PokemonVO get(int id) throws SQLException {
        String sql = "select * from pokemon where id = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                PokemonVO pokemon = new PokemonVO();

                pokemon.setId(rs.getInt("id"));
                pokemon.setName(rs.getString("name"));
                pokemon.setType(rs.getString("type"));
                pokemon.setOtherType(rs.getString("otherType"));
                pokemon.setHp(rs.getInt("hp"));
                pokemon.setAttack(rs.getInt("attack"));
                pokemon.setDefense(rs.getInt("defense"));

                return pokemon;
            }
        }

        return null;
    }

    @Override
    public int update(PokemonVO pokemon) throws SQLException {
        String sql = "update pokemon set name = ?, type = ?, otherType = ?, hp = ?, attack = ?, defense = ? where id = ?";
        int count = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pokemon.getName());
            pstmt.setString(2, pokemon.getType());
            pstmt.setString(3, pokemon.getOtherType());
            pstmt.setInt(4, pokemon.getHp());
            pstmt.setInt(5, pokemon.getAttack());
            pstmt.setInt(6, pokemon.getDefense());
            pstmt.setInt(7, pokemon.getId());

            count = pstmt.executeUpdate();
        }

        return count;
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "delete from pokemon where id = ?";
        int count = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            count = pstmt.executeUpdate();
        }

        return count;
    }
}
