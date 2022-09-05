package com.Alkemy.Disney.Characters.mapper;


import com.Alkemy.Disney.Characters.Repository.GeneroRepository;
import com.Alkemy.Disney.Characters.Repository.PeliculaRepository;
import com.Alkemy.Disney.Characters.Repository.PersonajeRespository;
import com.Alkemy.Disney.Characters.dto.PeliculaDTO;
import com.Alkemy.Disney.Characters.dto.PersonajeDTO;
import com.Alkemy.Disney.Characters.entity.PeliculaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PeliculaMap {


    //■ Imagen
    //■ Título
    //■ Fecha de creación
    //■ Calificación (del 1 al 5)
    //■ Personajes asociados

    @Autowired
    private PersonajeMap personajeMap;
    @Autowired
    private PersonajeRespository personajeRespository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;


    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto) {
        PeliculaEntity entity = new PeliculaEntity();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());

            for (PersonajeDTO personaje : dto.getPersonajes()) {
                entity.agregarPersonaje(personajeMap.personajeDTO2Entity(personaje));

        }
        return entity;
    }

//    private Set<PersonajeEntity> peliculaDTOList2EntitySet(List<Long> personajes) {
//        Set<PersonajeEntity> personajesSet = new HashSet<>();
//        for (Long id : personajes) {
//            personajesSet.add(personajeRespository.findById(id).get());
//        }
//        return personajesSet;
//    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity pelicula, boolean cargarPersonajes) {
        PeliculaDTO dto = new PeliculaDTO();

        dto.setId(pelicula.getId());
        dto.setImagen(pelicula.getImagen());
        dto.setTitulo(pelicula.getTitulo());
        dto.setFechaCreacion(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(pelicula.getFechaCreacion()));
        dto.setCalificacion(pelicula.getCalificacion());
        dto.setGeneroId(pelicula.getGeneroId());
        if (cargarPersonajes) {
            List<PersonajeDTO> personajeDTOList = personajeMap.personajeEntityList2DTOList(pelicula.getPersonajes(), false);
//            for (PersonajeDTO personajes : personajeDTOList) {
            System.out.println(personajeDTOList.size());
            System.out.println(pelicula.getPersonajes());
            dto.setPersonajes(personajeDTOList);
//            }
        }

        return dto;
    }


    public List<PeliculaDTO> peliculaEntitySet2DTOList(Collection<PeliculaEntity> entidades, boolean cargarPersonajes) {
        List<PeliculaDTO> listaDto = new ArrayList<>();
        for (PeliculaEntity entity : entidades) {
            listaDto.add(peliculaEntity2DTO(entity, cargarPersonajes));
        }
        return listaDto;
    }


    public List<PeliculaDTO> peliculaEntityList2DTOList(Collection<PeliculaEntity> entidades, boolean cargarPersonajes) {
        List<PeliculaDTO> peliculaDTOList = new ArrayList<>();
        for (PeliculaEntity entity : entidades) {
            peliculaDTOList.add(peliculaEntity2DTO(entity, cargarPersonajes));
        }
        return peliculaDTOList;
    }

    public PeliculaEntity peliculaActualizadaDTO2Entity(PeliculaDTO dto, Long id) {
        PeliculaEntity entity = peliculaRepository.findById(id).get();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(LocalDate.parse(dto.getFechaCreacion(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());
        return entity;
    }
}



