package org.mailwebservice.pokemonsystem.services;

import org.mailwebservice.pokemonsystem.entities.TrainerEntity;
import org.mailwebservice.pokemonsystem.repositories.PokemonRepository;
import org.mailwebservice.pokemonsystem.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerService {


    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PokemonRepository pokemonRepository;


    public void postTrainer(TrainerEntity trainerEntity) {
        trainerRepository.save(trainerEntity);

    }

    public List<TrainerEntity> getTrainers() {
        return trainerRepository.findAll();
    }

    public TrainerEntity getTrainerById(Long id) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer non trouvé");
        }
        return trainerRepository.findById(id).orElse(null);
    }

    public void deleteTrainerById(Long id) {
        if (!trainerRepository.existsById(id)) {
            throw new RuntimeException("Trainer non trouvé");
        }
        trainerRepository.deleteById(id);
    }

    public boolean hasMoreThanSixPokemons(Long trainerId) {
        System.out.println("Nombre de pokémons pour le trainer " + trainerId + ": " + pokemonRepository.countByTrainerId(trainerId));
        return pokemonRepository.countByTrainerId(trainerId) >= 6;
    }


    public void postCapturePokemon(Long pokemonId, Long trainerId) {
        if (!trainerRepository.existsById(trainerId)) {
            throw new RuntimeException("Trainer non trouvé");
        }
        if (!pokemonRepository.existsById(pokemonId)) {
            throw new RuntimeException("Pokémon non trouvé");
        }
        if (hasMoreThanSixPokemons(trainerId)) {
            throw new RuntimeException("Ce trainer a déjà 6 pokémons");
        }
        TrainerEntity trainer = trainerRepository.findById(trainerId).orElse(null);


        pokemonRepository.findById(pokemonId).ifPresent(pokemon -> {
            if (pokemon.getTrainer() != null) {
                throw new RuntimeException("Ce Pokémon est déjà capturé");
            }
            pokemon.setTrainer(trainer);
            pokemonRepository.save(pokemon);
        });
    }



    public Double getAverageLevelOfPokemons(Long trainerId) {
        if (!trainerRepository.existsById(trainerId)) {
            throw new RuntimeException("Trainer non trouvé");
        }
        Double avg = pokemonRepository.getAverageLevelByTrainerId(trainerId);
        return avg != null ? avg : 0.0;

    }


}
