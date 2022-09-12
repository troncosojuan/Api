package com.Alkemy.Disney.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
public class MovieDTO {



    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    @Max(5)
    @Min(1)
    private int calificacion;
    private Long generoId;
    private List<@Valid CharacterDTO> personajes;

    public void addPersonaje(CharacterDTO personaje) {
        personajes.add(personaje);
    }

    public void deletePelicula(CharacterDTO personaje) {
        personajes.remove(personaje);
    }
}
