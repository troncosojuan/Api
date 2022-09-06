package com.Alkemy.Disney.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name= "genero")
@Getter
@Setter

public class GenderEntity {

    //■ Nombre
    //■ Imagen
    //■ Películas o series asociadas


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private String imagen;


}
