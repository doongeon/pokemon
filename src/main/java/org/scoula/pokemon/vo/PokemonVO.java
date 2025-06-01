package org.scoula.pokemon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonVO {
    int id;
    String name;
    String type;
    String otherType;
    int hp;
    int attack;
    int defense;
}
