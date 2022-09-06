package com.Alkemy.Disney.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CharacterFiltersDTO {

    private String nombre;
    private Integer edad;
    private Set<Long> idPeliculas;

    public CharacterFiltersDTO(String nombre, Integer edad, Set<Long> idPeliculas) {
        this.nombre = nombre;
        this.edad = edad;
        this.idPeliculas = idPeliculas;
    }
}
