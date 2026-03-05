package org.mailwebservice.pokemonsystem.services;

import org.mailwebservice.pokemonsystem.entities.PokemonEntity;
import org.mailwebservice.pokemonsystem.entities.TrainerEntity;
import org.mailwebservice.pokemonsystem.enumerations.PokemonType;
import org.mailwebservice.pokemonsystem.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;


    public void postPokemon(PokemonEntity pokemonEntity) {
        if (pokemonEntity == null) {
            throw new RuntimeException("Le pokemon ne peut pas être null");
        }
        pokemonRepository.save(pokemonEntity);
    }

    public List<PokemonEntity> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public void deletePokemonById(Long id) {
        if (pokemonRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Le pokemon avec l'id " + id + " n'existe pas");
        }
        pokemonRepository.deleteById(id);
    }

    public List<PokemonEntity> getPokemonsByType(String type) {

        if (PokemonType.valueOf(type.toUpperCase()) == null) {
            throw new IllegalArgumentException("Type de pokemon invalide : " + type);
        }
        return pokemonRepository.findByType(PokemonType.valueOf(type.toUpperCase()));
    }

}
