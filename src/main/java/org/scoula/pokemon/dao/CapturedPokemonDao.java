package org.scoula.pokemon.dao;

import org.scoula.pokemon.vo.CapturedPokemonVO;

import java.sql.SQLException;
import java.util.List;

public interface CapturedPokemonDao {
    int insert(CapturedPokemonVO myPokemon) throws SQLException;

    List<CapturedPokemonVO> list() throws SQLException;

    CapturedPokemonVO get(int id) throws SQLException;

    int updateName(CapturedPokemonVO myPokemon) throws SQLException;

    int delete(int id) throws SQLException;
}
