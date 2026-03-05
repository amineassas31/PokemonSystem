package org.mailwebservice.pokemonsystem.controllers;

import org.mailwebservice.pokemonsystem.entities.TrainerEntity;
import org.mailwebservice.pokemonsystem.repositories.PokemonRepository;
import org.mailwebservice.pokemonsystem.services.ProfileDtoService;
import org.mailwebservice.pokemonsystem.services.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/trainers")
public class TrainerController {


    @Autowired
    TrainerService trainerService;

    @Autowired
    PokemonRepository pokemonRepository;

    @Autowired
    ProfileDtoService profileDtoService;

    @PostMapping
    public ResponseEntity getTrainer(@RequestBody TrainerEntity trainerEntity) {
        try {
            trainerService.postTrainer(trainerEntity);
            return new ResponseEntity<>("Trainer ajouté", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erreur lors de l'ajout du trainer : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity getTrainers() {
        try {
            return new ResponseEntity<>(trainerService.getTrainers(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erreur lors de la récupération des trainers : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getTrainerById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(trainerService.getTrainerById(id), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erreur lors de la récupération du trainer : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTrainerById(@PathVariable Long id) {
        try {
            trainerService.deleteTrainerById(id);
            return new ResponseEntity<>("Trainer supprimé", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Erreur lors de la suppression du trainer : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{trainerId}/capture/{pokemonId}")
    public ResponseEntity postCapturePokemon(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
        try {
            trainerService.postCapturePokemon(pokemonId, trainerId);
            return ResponseEntity.ok("Pokemon capturé");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de la capture du pokemon : " + e.getMessage());
        }
    }

    @GetMapping("{id}/average-level")
    public ResponseEntity getAverageLevelOfPokemons(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(trainerService.getAverageLevelOfPokemons(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération du niveau moyen des pokemons : " + e.getMessage());
        }
    }


    @GetMapping("/{id}/profile")
    public ResponseEntity getProfile(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(profileDtoService.createProfileDto(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Erreur lors de la récupération du profil : " + e.getMessage());
        }
    }
}
