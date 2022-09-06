package com.Alkemy.Disney.dto;

import lombok.Data;

import java.util.List;

@Data

public class CharacterDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    private String historia;
    private List<MovieDTO> peliculas;

}


