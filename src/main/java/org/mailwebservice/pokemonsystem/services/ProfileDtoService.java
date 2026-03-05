package org.mailwebservice.pokemonsystem.services;

import org.mailwebservice.pokemonsystem.dto.ProfileDto;
import org.mailwebservice.pokemonsystem.entities.TrainerEntity;
import org.mailwebservice.pokemonsystem.repositories.PokemonRepository;
import org.mailwebservice.pokemonsystem.repositories.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileDtoService {

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PokemonRepository pokemonRepository;


    public ProfileDto createProfileDto(Long trainerId) {
        if (!trainerRepository.existsById(trainerId)) {
            throw new RuntimeException("Trainer non trouvé");
        }
        ProfileDto profileDto = new ProfileDto();
        TrainerEntity trainer = trainerRepository.findById(trainerId).orElse(null);
        if (trainer != null) {
            profileDto.setName(trainer.getName());
            profileDto.setCity(trainer.getCity());
            profileDto.setExperiencePoints(trainer.getExperiencePoints());
            profileDto.setPokemons(pokemonRepository.findByTrainerId(trainerId));
            profileDto.setFavoriteType(pokemonRepository.findFavoriteTypeByTrainerId(trainerId));

            Double powerScore = pokemonRepository.calculatePowerScoreByTrainerId(trainerId);
            if (pokemonRepository.differentTypesCountByTrainerId(trainerId) >= 3) {
                profileDto.setPowerScore(powerScore*1.1);
            } else {
                profileDto.setPowerScore(powerScore);
            }
            if (pokemonRepository.countByTrainerId(trainerId) >= 6 && pokemonRepository.getAverageLevelByTrainerId(trainerId) > 75) {
                profileDto.setLegend(true);
            } else {
                profileDto.setLegend(false);
            }

        }
        return profileDto;
    }



}
