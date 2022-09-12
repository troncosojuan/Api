package com.Alkemy.Disney.service.impl;


import com.Alkemy.Disney.dto.GenderDTO;
import com.Alkemy.Disney.entity.GenderEntity;
import com.Alkemy.Disney.mapper.GenderMap;
import com.Alkemy.Disney.repository.GenderRepository;
import com.Alkemy.Disney.service.GenderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class GenderServiceImpl implements GenderService {


    @Autowired
    public GenderMap genderMap;
    @Autowired
    private GenderRepository genderRepository;


    public GenderDTO save(GenderDTO dto) {
        GenderEntity entity = genderMap.genderDTO2Entity(dto);
        GenderEntity entitySaved = genderRepository.save(entity);
        GenderDTO GenderDTO = genderMap.genderEntity2DTO(entitySaved);
        return GenderDTO;
    }


    @Override
    public List<GenderDTO> allGeneros() {
        List<GenderEntity> entity = genderRepository.findAll();
        List<GenderDTO> dtoList = genderMap.genderEntityList2DTOList(entity);
        return dtoList;

    }
}
