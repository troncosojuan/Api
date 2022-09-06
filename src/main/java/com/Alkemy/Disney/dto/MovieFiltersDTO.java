package com.Alkemy.Disney.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MovieFiltersDTO {
    private String titulo;
    private Set<Long> idGenero;
    private String order;

    public MovieFiltersDTO(String titulo, Set<Long> idGenero, String order) {
        this.titulo = titulo;
        this.idGenero = idGenero;
        this.order = order;
    }

    public boolean isASC() {
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return order.compareToIgnoreCase("DESC") == 0;
    }
}

