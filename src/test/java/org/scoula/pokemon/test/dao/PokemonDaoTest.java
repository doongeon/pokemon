package org.scoula.pokemon.test.dao;

import org.junit.jupiter.api.*;
import org.scoula.pokemon.common.JDBCUtil;
import org.scoula.pokemon.dao.PokemonDao;
import org.scoula.pokemon.dao.PokemonDaoImp;
import org.scoula.pokemon.vo.PokemonVO;

import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PokemonDaoTest {
    PokemonDao pokemonDao = new PokemonDaoImp();

    @Test
    @DisplayName("insert pokemon")
    @Order(1)
    public void insertPokemon() throws SQLException {
        PokemonVO pokemon = new PokemonVO(0, "test","testType", "testOtherType", 1, 1, 1);

        int count = pokemonDao.create(pokemon);

        Assertions.assertEquals(1, count);
    }

    @Test
    @DisplayName("select pokemons")
    @Order(2)
    public void selectPokemons() throws SQLException{
        List<PokemonVO> list = pokemonDao.getList();

        for(PokemonVO p : list) System.out.println(p);
    }

    @Test
    @DisplayName("select pokemon by id")
    @Order(2)
    public void selectPokemonById() throws SQLException{
        int id = 5;
        PokemonVO pokemon = pokemonDao.get(id);

        System.out.println(pokemon);
    }

    @Test
    @DisplayName("update pokemon")
    @Order(3)
    public void updatePokemon() throws SQLException {
        PokemonVO pokemon = pokemonDao.get(5);
        pokemon.setName("updatedName");

        int count = pokemonDao.update(pokemon);
        Assertions.assertEquals(1, count);
    }

    @Test
    @DisplayName("delete pokemon")
    @Order(4)
    public void deletePokemon() throws SQLException {
        int id = 6;

        int count = pokemonDao.delete(id);
        Assertions.assertEquals(1, count);
    }

    @AfterAll
    public static void tearDown() {
        JDBCUtil.close();
    }

}
