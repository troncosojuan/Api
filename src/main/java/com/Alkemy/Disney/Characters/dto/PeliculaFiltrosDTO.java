package com.Alkemy.Disney.Characters.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PeliculaFiltrosDTO {
    private String titulo;
    private Set<Long> idGenero;
    private String order;

    public PeliculaFiltrosDTO(String titulo, Set<Long> idGenero, String order) {
        this.titulo = titulo;
        this.idGenero = idGenero;
        this.order = order;
    }

    public boolean isASC(){ return order.compareToIgnoreCase("ASC") == 0;}

    public boolean isDESC(){ return order.compareToIgnoreCase("DESC") == 0;}
}

