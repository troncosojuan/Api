package com.Alkemy.Disney.mapper;


import com.Alkemy.Disney.dto.GenderDTO;
import com.Alkemy.Disney.entity.GenderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenderMap {

    public GenderEntity genderDTO2Entity(GenderDTO dto){
        GenderEntity entity = new GenderEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    public GenderDTO genderEntity2DTO(GenderEntity entity) {
        GenderDTO dto = new GenderDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public List<GenderDTO> genderEntityList2DTOList(List<GenderEntity> entities) {
        List<GenderDTO> genderListDto = new ArrayList<>();
        for (GenderEntity gender : entities) {
            genderListDto.add(genderEntity2DTO(gender));
        }
        return genderListDto;
    }
}
