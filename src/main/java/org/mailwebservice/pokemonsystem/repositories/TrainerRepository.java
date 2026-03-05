package org.mailwebservice.pokemonsystem.repositories;

import org.mailwebservice.pokemonsystem.entities.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity,Long> {
}
