package com.Alkemy.Disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "personaje")

@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter

public class CharacterEntity {


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
    private Integer edad;
    private Double peso;
    private String historia;
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes")
    private Set<MovieEntity> peliculas = new HashSet<>();

    public void agregarPelicula(MovieEntity pelicula) {
        peliculas.add(pelicula);

    }

    public void borrarPelicula(MovieEntity pelicula) {
        peliculas.remove(pelicula);
    }
}
