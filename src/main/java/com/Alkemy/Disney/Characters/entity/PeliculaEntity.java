package com.Alkemy.Disney.Characters.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
@Table(name = "pelicula")

@Entity
public class PeliculaEntity {

    //■ Imagen
    //■ Título
    //■ Fecha de creación
    //■ Calificación (del 1 al 5)
    //■ Personajes asociados

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String titulo;

    @Column(name = "fecha_creacion")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaCreacion;

    private boolean deleted = Boolean.FALSE;

    private Integer calificacion;


    //generamos relacion con genero
    @ManyToOne(fetch = FetchType.EAGER)
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


//    private List<Long> listPersonajes = new ArrayList<>();


    public void agregarPersonaje(PersonajeEntity personaje) {
        this.personajes.add(personaje);
    }

    public void borrarPersonaje(PersonajeEntity personaje) {
        this.personajes.remove(personaje);
        personaje.borrarPelicula(this);
    }


}
