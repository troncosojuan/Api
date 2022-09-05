package com.Alkemy.Disney.Characters.dto;

import com.Alkemy.Disney.Characters.entity.PeliculaEntity;
import lombok.Data;

import java.util.List;

@Data

public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private Double peso;
    private String historia;
    private List<PeliculaDTO> peliculas;

    public void addPelicula(PeliculaDTO pelicula) {
        peliculas.add(pelicula);
    }

    public void deletePelicula(PeliculaDTO pelicula) {
        peliculas.remove(pelicula);
    }
}


