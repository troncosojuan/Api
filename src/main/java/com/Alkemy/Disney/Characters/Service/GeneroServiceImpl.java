package com.Alkemy.Disney.Characters.Service;


import com.Alkemy.Disney.Characters.Repository.GeneroRepository;
import com.Alkemy.Disney.Characters.Service.impl.GeneroService;
import com.Alkemy.Disney.Characters.dto.GeneroDTO;
import com.Alkemy.Disney.Characters.entity.GeneroEntity;
import com.Alkemy.Disney.Characters.mapper.GeneroMap;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class GeneroServiceImpl implements GeneroService {


    @Autowired
    public GeneroMap generoMap;
    @Autowired
    private GeneroRepository generoRepository;


    //PROPOSITO:
    //Guarda una entidad en el repositorio, devuelve un dto
    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity entity = generoMap.generoDTO2Entity(dto);
        GeneroEntity entitySaved = generoRepository.save(entity);
        GeneroDTO GeneroDTO = generoMap.generoEntity2DTO(entitySaved);
        return GeneroDTO;
    }

    //PROPOSITO:
    //muestra una lista con todos los generos, devuelve una lista
    @Override
    public List<GeneroDTO> allGeneros() {
        List<GeneroEntity> entity = generoRepository.findAll();
        List<GeneroDTO> listaDto = generoMap.generoEntityList2DTOList(entity);
        return listaDto;

    }
}
