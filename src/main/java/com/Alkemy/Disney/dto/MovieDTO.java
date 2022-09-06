package com.Alkemy.Disney.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {



    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private int calificacion;
    private Long generoId;
    private List<CharacterDTO> personajes;

    public void addPersonaje(CharacterDTO personaje) {
        personajes.add(personaje);
    }

    public void deletePelicula(CharacterDTO personaje) {
        personajes.remove(personaje);
    }
}
