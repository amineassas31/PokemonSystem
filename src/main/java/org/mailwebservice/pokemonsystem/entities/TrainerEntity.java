package org.mailwebservice.pokemonsystem.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;


@Entity
@Table(name = "Trainer")
public class TrainerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nationalized
    @Column(name = "name", length = 100)
    private String name;

    @Nationalized
    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "experience_points")
    private Integer experiencePoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}