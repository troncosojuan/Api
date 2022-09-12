package com.Alkemy.Disney.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.List;

@Data

public class CharacterDTO {

    private Long id;
    private String imagen;
    private String nombre;
    @Max(110)
    @Min(1)
    private Integer edad;
    @Positive
    private Double peso;
    private String historia;
    private List<@Valid MovieDTO> peliculas;

}


