package org.mailwebservice.pokemonsystem.repositories;

import org.mailwebservice.pokemonsystem.entities.PokemonEntity;
import org.mailwebservice.pokemonsystem.enumerations.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {

    @Query("SELECT p FROM PokemonEntity p WHERE p.type = :type")
    List<PokemonEntity> findByType(PokemonType type);


    long countByTrainerId(Long trainerId);

    @Query("SELECT AVG(CAST(p.level AS double)) FROM PokemonEntity p WHERE p.trainer.id = :trainerId")
    Double getAverageLevelByTrainerId(Long trainerId);


    List<PokemonEntity> findByTrainerId(Long trainerId);

    @Query("SELECT p.type FROM PokemonEntity p WHERE p.trainer.id = :trainerId GROUP BY p.type ORDER BY COUNT(p) DESC LIMIT 1")
    PokemonType findFavoriteTypeByTrainerId(Long trainerId);

    @Query("SELECT SUM(p.level) FROM PokemonEntity p WHERE p.trainer.id = :trainerId")
    Double calculatePowerScoreByTrainerId(Long trainerId);

    @Query("SELECT COUNT(DISTINCT p.type) FROM PokemonEntity p WHERE p.trainer.id = :trainerId")
    Integer differentTypesCountByTrainerId(Long trainerId);
}
