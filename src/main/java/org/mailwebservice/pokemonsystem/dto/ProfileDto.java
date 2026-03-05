package org.mailwebservice.pokemonsystem.dto;

import org.mailwebservice.pokemonsystem.entities.PokemonEntity;
import org.mailwebservice.pokemonsystem.enumerations.PokemonType;

import java.util.List;


public class ProfileDto {
    private String name;
    private String city;
    private Integer experiencePoints;
    private List<PokemonEntity> pokemons;
    private PokemonType favoriteType;
    private Double powerScore;
    private Boolean isLegend;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(Integer experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public List<PokemonEntity> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<PokemonEntity> pokemons) {
        this.pokemons = pokemons;
    }

    public PokemonType getFavoriteType() {
        return favoriteType;
    }

    public void setFavoriteType(PokemonType favoriteType) {
        this.favoriteType = favoriteType;
    }

    public Double getPowerScore() {
        return powerScore;
    }

    public void setPowerScore(Double powerScore) {
        this.powerScore = powerScore;
    }

    public Boolean getLegend() {
        return isLegend;
    }

    public void setLegend(Boolean legend) {
        isLegend = legend;
    }
}
