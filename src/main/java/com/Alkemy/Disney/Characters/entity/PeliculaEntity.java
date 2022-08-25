package com.Alkemy.Disney.Characters.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "pelicula")


public class PeliculaEntity {

    //■ Imagen
    //■ Título
    //■ Fecha de creación
    //■ Calificación (del 1 al 5)
    //■ Personajes asociados

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "dd/MM/yyy")
    private LocalDate fechaCreacion;

    private int calificacion;


    //generamos relacion con genero
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;

    //generamos relacion con personajes
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })

    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();






}
