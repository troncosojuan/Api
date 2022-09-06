package com.Alkemy.Disney.Service;

import com.Alkemy.Disney.Repository.CharacterRespository;
import com.Alkemy.Disney.Repository.specifications.CharacterSpecification;
import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.dto.CharacterFiltersDTO;
import com.Alkemy.Disney.entity.CharacterEntity;
import com.Alkemy.Disney.mapper.CharacterMap;
import com.Alkemy.Disney.Service.impl.CharacterService;
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

    //PROPOSITO:
    //Guarda una entidad en el repositorio. nos devuelve el dto

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMap.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRespository.save(entity);
        CharacterDTO characterDTO = characterMap.characterEntity2DTO(entitySaved, false);
        return characterDTO;
    }

    //PROPOSITO:
    //Busca en el repositorio una entidad, si no la encuentra devuelve un dto nulo y si la encuentra devuelve un dto con los datos de la entidad
    @Override
    public boolean search(Long id) {

        Boolean characterExist;
        if (characterRespository.existsById(id)) {
            characterExist = true;
        } else {
            characterExist = false;
        }
        return characterExist;
    }


    //PROPOSITO:
    //Genera un update con el id de una entidad y un dto, devuelve la entidad actualizada como dto
    // parametro: CharacterDto, Long
    @Override
    public CharacterEntity update(CharacterDTO dto, Long id) {
        CharacterEntity entity = characterMap.characterDTO2Entity(dto);
        entity.setId(id);
        characterRespository.save(entity);

        return entity;
    }

    //PROPOSITO:
    //Borra una entidad por su numero id
    //parametro: id pelicula
    @Override
    public void delete(Long id) {
        characterRespository.deleteById(id);
    }

    @Override
    public List<CharacterDTO> searchCharacterByFilters(String name, Integer age, Set<Long> idMovie) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, idMovie);
        List<CharacterEntity> entities = characterRespository.findAll(characterSpecification.searchByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMap.characterEntityCollection2DTOList(entities, true);
        return dtos;
    }


}






