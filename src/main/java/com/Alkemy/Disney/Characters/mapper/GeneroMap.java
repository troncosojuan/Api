package com.Alkemy.Disney.Characters.mapper;


import com.Alkemy.Disney.Characters.dto.GeneroDTO;
import com.Alkemy.Disney.Characters.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMap {

    public GeneroEntity generoDTO2Entity(GeneroDTO dto){
        GeneroEntity entity = new GeneroEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entities) {
        List<GeneroDTO> generoListDto = new ArrayList<>();
        for (GeneroEntity genero: entities) {
            generoListDto.add(generoEntity2DTO(genero));
        }
        return generoListDto;
    }
}
