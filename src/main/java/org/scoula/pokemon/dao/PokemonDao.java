package org.scoula.pokemon.dao;

import org.scoula.pokemon.vo.PokemonVO;

import java.sql.SQLException;
import java.util.List;

public interface PokemonDao {
    int create(PokemonVO pokemon) throws SQLException;

    List<PokemonVO> getList() throws SQLException;

    PokemonVO get(int id) throws SQLException;

    int update(PokemonVO pokemon) throws SQLException;

    int delete(int id) throws SQLException;
}
