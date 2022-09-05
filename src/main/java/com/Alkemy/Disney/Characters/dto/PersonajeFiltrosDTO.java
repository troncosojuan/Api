package com.Alkemy.Disney.Characters.dto;

import lombok.Data;

import java.util.Set;

@Data
public class PersonajeFiltrosDTO {

    private String nombre;
    private int edad;
    private Set<Long> idPeliculas;

    public PersonajeFiltrosDTO(String nombre, int edad, Set<Long> idPeliculas) {
        this.nombre = nombre;
        this.edad = edad;
        this.idPeliculas = idPeliculas;
    }
}
