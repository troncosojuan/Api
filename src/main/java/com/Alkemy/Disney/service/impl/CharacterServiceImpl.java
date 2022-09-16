package com.Alkemy.Disney.service.impl;

import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.dto.CharacterDTOBasic;
import com.Alkemy.Disney.dto.CharacterFiltersDTO;
import com.Alkemy.Disney.entity.CharacterEntity;
import com.Alkemy.Disney.exception.ParamNotFound;
import com.Alkemy.Disney.mapper.CharacterMap;
import com.Alkemy.Disney.repository.CharacterRespository;
import com.Alkemy.Disney.repository.specifications.CharacterSpecification;
import com.Alkemy.Disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMap characterMap;
    @Autowired
    private CharacterRespository characterRespository;
    @Autowired
    private CharacterSpecification characterSpecification;

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMap.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRespository.save(entity);
        CharacterDTO characterDTO = characterMap.characterEntity2DTO(entitySaved, false);
        return characterDTO;
    }


    @Override
    public CharacterDTO update(CharacterDTO dto, Long id) throws ParamNotFound {
        CharacterDTO characterDTO;
        if (characterRespository.existsById(id)) {
            CharacterEntity characterEntity = characterMap.characterUpdatedDTO2Entity(dto, id);
            CharacterEntity characterUpdated = characterRespository.save(characterEntity);
            characterDTO = characterMap.characterEntity2DTO(characterUpdated, true);
        } else {
            throw new ParamNotFound("Id character no valido");
        }
        return characterDTO;
    }


    public void delete(Long id) throws ParamNotFound {
        if (characterRespository.existsById(id)) {
            characterRespository.deleteById(id);
        } else {
            throw new ParamNotFound("Id character no valido");
        }
    }

    @Override
    public List<CharacterDTOBasic> searchCharacterByFilters(String name, Integer age, Set<Long> idMovie) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, idMovie);
        List<CharacterEntity> entities = characterRespository.findAll(characterSpecification.searchByFilters(filtersDTO));
        List<CharacterDTOBasic> dtos = characterMap.characterEntityCollection2DTOBasicList(entities);
        return dtos;
    }


}






