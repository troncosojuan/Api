package com.Alkemy.Disney.Characters.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "genero")
@Getter
@Setter

public class GeneroEntity {

    //■ Nombre
    //■ Imagen
    //■ Películas o series asociadas


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private String imagen;


}
