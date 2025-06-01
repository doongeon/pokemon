package org.scoula.pokemon.csv;

import org.scoula.pokemon.dao.PokemonDao;
import org.scoula.pokemon.dao.PokemonDaoImp;
import org.scoula.pokemon.vo.PokemonVO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class CSVReader {
    public static void main(String[] args) {
        String filePath = "/Users/donggeon/Downloads/Pokemon.csv"; // 실제 파일 경로로 바꿔주세요
        PokemonDao pokemonDao = new PokemonDaoImp();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); // 쉼표 안의 따옴표 고려

                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String name = fields[1];
                String type = fields[2];
                String otherType = fields[3];
                int hp = Integer.parseInt(fields[5]);
                int attack = Integer.parseInt(fields[6]);
                int defense = Integer.parseInt(fields[7]);

                PokemonVO pokemon = new PokemonVO();
                pokemon.setName(name);
                pokemon.setType(type);
                pokemon.setOtherType(otherType);
                pokemon.setHp(hp);
                pokemon.setAttack(attack);
                pokemon.setDefense(defense);

                try {
                    pokemonDao.create(pokemon);
                } catch (SQLException e) {
                    System.out.println(pokemon);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
