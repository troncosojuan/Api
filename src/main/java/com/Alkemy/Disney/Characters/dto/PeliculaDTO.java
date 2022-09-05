package com.Alkemy.Disney.Characters.dto;

import com.Alkemy.Disney.Characters.entity.GeneroEntity;
import com.Alkemy.Disney.Characters.entity.PersonajeEntity;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PeliculaDTO {



    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private int calificacion;
    private Long generoId;
    private List<PersonajeDTO> personajes;

    public void addPersonaje(PersonajeDTO personaje) {
        personajes.add(personaje);
    }

    public void deletePelicula(PersonajeDTO personaje) {
        personajes.remove(personaje);
    }
}
