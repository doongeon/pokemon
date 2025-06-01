package org.scoula.pokemon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapturedPokemonVO {
    int id;
    int pokemonId;
    String name;
}
