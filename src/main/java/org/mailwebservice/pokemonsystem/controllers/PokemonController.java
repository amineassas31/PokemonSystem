package org.mailwebservice.pokemonsystem.controllers;

import org.mailwebservice.pokemonsystem.entities.PokemonEntity;
import org.mailwebservice.pokemonsystem.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @PostMapping
    public ResponseEntity postPokemon(@RequestBody PokemonEntity pokemonEntity) {
        try {
            pokemonService.postPokemon(pokemonEntity);
            return ResponseEntity.status(201).body("Pokemon ajouté");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout du pokemon : " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllPokemons() {
        try {
            return ResponseEntity.ok(pokemonService.getAllPokemons());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des pokemons : " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePokemonById(@PathVariable Long id) {
        try {
            pokemonService.deletePokemonById(id);
            return ResponseEntity.ok("Pokemon supprimé");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de la suppression du pokemon : " + e.getMessage());
        }
    }


    @GetMapping("/type/{type}")
    public ResponseEntity getPokemonsByType(@PathVariable String type) {
        try {
            return ResponseEntity.ok(pokemonService.getPokemonsByType(type));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Type de pokemon invalide : " + type);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération des pokemons par type : " + e.getMessage());
        }
    }



}
