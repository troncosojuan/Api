package com.Alkemy.Disney.mapper;

import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.dto.CharacterDTOBasic;
import com.Alkemy.Disney.dto.MovieDTO;
import com.Alkemy.Disney.entity.CharacterEntity;
import com.Alkemy.Disney.repository.CharacterRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CharacterMap {

    @Autowired
    private MovieMap movieMap;
    @Autowired
    private CharacterRespository characterRespository;


    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
        CharacterEntity entity = new CharacterEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
        return entity;
    }


    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean cargarPeliculas) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if (cargarPeliculas) {
            List<MovieDTO> movieDTOList = movieMap.movieEntityCollection2DTOList(entity.getPeliculas(), false);
            dto.setPeliculas(movieDTOList);
        }
        return dto;
    }


    public List<CharacterDTO> characterEntityCollection2DTOList(Collection<CharacterEntity> entidades, boolean cargarPersonajes) {
        List<CharacterDTO> characterDTOList = new ArrayList<>();
        for (CharacterEntity entity : entidades) {
            characterDTOList.add(characterEntity2DTO(entity, cargarPersonajes));
        }
        return characterDTOList;
    }

    public CharacterEntity characterUpdatedDTO2Entity(CharacterDTO dto, Long id) {
        CharacterEntity entity = characterRespository.findById(id).get();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());
        return entity;
    }

    public List<CharacterDTOBasic> characterEntityCollection2DTOBasicList(List<CharacterEntity> entities) {
        List<CharacterDTOBasic> DTOBasicList = new ArrayList<>();
        for (CharacterEntity entity : entities) {
            DTOBasicList.add(characterEntity2DTOBasic(entity));
        }
        return DTOBasicList;
    }

    private CharacterDTOBasic characterEntity2DTOBasic(CharacterEntity entity) {
        CharacterDTOBasic dto = new CharacterDTOBasic();
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}