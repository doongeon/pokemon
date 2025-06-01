package org.scoula.pokemon.test.dao;


import org.junit.jupiter.api.*;
import org.scoula.pokemon.common.JDBCUtil;
import org.scoula.pokemon.dao.CapturedPokemonDao;
import org.scoula.pokemon.dao.CapturedPokemonDaoImp;
import org.scoula.pokemon.test.crud.CapturedPokemon;
import org.scoula.pokemon.vo.CapturedPokemonVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CapturedPokenomDaoTest {
    public CapturedPokemonDao cpd = new CapturedPokemonDaoImp();
    private final int POKEMON_ID = 806;


    @Test
    @DisplayName("insert my pokemon")
    @Order(1)
    public void insertPokemon() throws SQLException {
        CapturedPokemonVO myPokemon = new CapturedPokemonVO();
        myPokemon.setName("test");
        myPokemon.setPokemonId(POKEMON_ID);

        int count = cpd.insert(myPokemon);
        Assertions.assertEquals(1, count);
    }

    @Test
    @DisplayName("select my pokemons")
    @Order(2)
    public void selectMyPokemons() throws SQLException {
        List<CapturedPokemonVO> l =  cpd.list();

        for(CapturedPokemonVO p : l) {
            System.out.println(p);
        }
    }

    @Test
    @DisplayName("select my pokemon")
    @Order(3)
    public void selectMyPokemon() throws SQLException {
        int id = 3;

        CapturedPokemonVO myPokemon = cpd.get(id);
        System.out.println(myPokemon);
    }

    @Test
    @DisplayName("update name of my pokemon")
    @Order(4)
    public void updateMyPokemonName() throws SQLException {
        int id = 3;
        String name = "updatedName";

        CapturedPokemonVO myPokemon = new CapturedPokemonVO();
        myPokemon.setId(id);
        myPokemon.setPokemonId(POKEMON_ID);
        myPokemon.setName(name);

        int count = cpd.updateName(myPokemon);
        Assertions.assertEquals(1, count);
    }

    @Test
    @DisplayName("delete my pokemon")
    @Order(5)
    public void deleteMyPokemon() throws SQLException {
        int id = 3;

        int count = cpd.delete(id);
        Assertions.assertEquals(1, count);
    }


    @AfterAll
    public static void tearDown() {
        JDBCUtil.close();
    }

}
