package com.Alkemy.Disney.Characters.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter
public class PersonajeEntity {


    //○ Imagen
    //○ Nombre
    //○ Edad
    //○ Peso
    //○ Historia
    //○ Películas o series asociadas

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String nombre;
    private int edad;
    private Double peso;
    private String historia;

    @ManyToMany(mappedBy = "personajes",  cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    private Set<PeliculaEntity> peliculas = new HashSet<>();

}
