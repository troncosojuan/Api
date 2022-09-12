package com.Alkemy.Disney.mapper;


import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.dto.MovieDTO;
import com.Alkemy.Disney.dto.MovieDTOBasic;
import com.Alkemy.Disney.entity.MovieEntity;
import com.Alkemy.Disney.repository.CharacterRespository;
import com.Alkemy.Disney.repository.GenderRepository;
import com.Alkemy.Disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class MovieMap {


    //■ Imagen
    //■ Título
    //■ Fecha de creación
    //■ Calificación (del 1 al 5)
    //■ Personajes asociados

    @Autowired
    private CharacterMap characterMap;
    @Autowired
    private CharacterRespository characterRespository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MovieRepository movieRepository;


    public MovieEntity movieDTO2Entity(MovieDTO dto) {
        MovieEntity entity = new MovieEntity();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());

        for (CharacterDTO personaje : dto.getPersonajes()) {
            entity.agregarPersonaje(characterMap.characterDTO2Entity(personaje));

        }
        return entity;
    }


    public MovieDTO movieEntity2DTO(MovieEntity pelicula, boolean cargarPersonajes) {
        MovieDTO dto = new MovieDTO();

        dto.setId(pelicula.getId());
        dto.setImagen(pelicula.getImagen());
        dto.setTitulo(pelicula.getTitulo());
        dto.setFechaCreacion(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(pelicula.getFechaCreacion()));
        dto.setCalificacion(pelicula.getCalificacion());
        dto.setGeneroId(pelicula.getGeneroId());
        if (cargarPersonajes) {
            List<CharacterDTO> characterDTOList = characterMap.characterEntityCollection2DTOList(pelicula.getPersonajes(), false);
            dto.setPersonajes(characterDTOList);
        }
        return dto;
    }


    public List<MovieDTO> movieEntityCollection2DTOList(Collection<MovieEntity> entities, boolean cargarPersonajes) {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (MovieEntity entity : entities) {
            movieDTOList.add(movieEntity2DTO(entity, cargarPersonajes));
        }
        return movieDTOList;
    }


    public MovieEntity movieUpdatedDTO2Entity(MovieDTO dto, Long id) {
        MovieEntity entity = movieRepository.findById(id).get();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
        return entity;
    }

    public List<MovieDTOBasic> movieEntityCollection2BasicDTOList(List<MovieEntity> entities) {
        List<MovieDTOBasic> movieDTOBasics = new ArrayList<>();
        for (MovieEntity entity : entities) {
            movieDTOBasics.add(movieEntity2DTOBasic(entity));
        }
        return movieDTOBasics;
    }

    private MovieDTOBasic movieEntity2DTOBasic(MovieEntity entity) {
        MovieDTOBasic dtoBasic = new MovieDTOBasic();
        dtoBasic.setImagen(entity.getImagen());
        dtoBasic.setTitulo(entity.getTitulo());
        dtoBasic.setFechaCreacion(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(entity.getFechaCreacion()));
        return dtoBasic;
    }
}



