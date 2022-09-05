package com.Alkemy.Disney.Characters.mapper;

import com.Alkemy.Disney.Characters.Repository.PeliculaRepository;
import com.Alkemy.Disney.Characters.dto.PeliculaDTO;
import com.Alkemy.Disney.Characters.dto.PersonajeDTO;
import com.Alkemy.Disney.Characters.entity.PeliculaEntity;
import com.Alkemy.Disney.Characters.entity.PersonajeEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMap {

    @Autowired
    private PeliculaMap peliculaMap;
    @Autowired
    private PeliculaRepository peliculaRepository;


    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

        return entity;
    }


    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean cargarPeliculas) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if (cargarPeliculas) {
            List<PeliculaDTO> peliculaDTOList = peliculaMap.peliculaEntitySet2DTOList(entity.getPeliculas(), false);
                dto.setPeliculas(peliculaDTOList);
        }
        return dto;
    }

    public List<PersonajeDTO> personajeEntitySet2DTOList(Collection<PersonajeEntity> entidades, boolean cargarPeliculas) {
        List<PersonajeDTO> listaDto = new ArrayList<>();
        for (PersonajeEntity entity : entidades) {
            listaDto.add(personajeEntity2DTO(entity, cargarPeliculas));
        }
        return listaDto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(Collection<PersonajeEntity> entidades, boolean cargarPersonajes) {
        List<PersonajeDTO> personajeDTOList = new ArrayList<>();
        for (PersonajeEntity entity : entidades){
            personajeDTOList.add(personajeEntity2DTO(entity, cargarPersonajes));
        }
        return personajeDTOList;
    }
}