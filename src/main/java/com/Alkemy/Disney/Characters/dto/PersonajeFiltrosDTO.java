package com.Alkemy.Disney.Characters.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PersonajeFiltrosDTO {

    private String nombre;
    private Integer edad;
    private Set<Long> idPeliculas;

    public PersonajeFiltrosDTO(String nombre, Integer edad, Set<Long> idPeliculas) {
        this.nombre = nombre;
        this.edad = edad;
        this.idPeliculas = idPeliculas;
    }
}
