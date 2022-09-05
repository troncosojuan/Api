package com.Alkemy.Disney.Characters.Service;

import com.Alkemy.Disney.Characters.Repository.PersonajeRespository;
import com.Alkemy.Disney.Characters.Repository.specifications.PersonajeSpecification;
import com.Alkemy.Disney.Characters.dto.PersonajeDTO;
import com.Alkemy.Disney.Characters.dto.PersonajeFiltrosDTO;
import com.Alkemy.Disney.Characters.entity.PersonajeEntity;
import com.Alkemy.Disney.Characters.mapper.PersonajeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements com.Alkemy.Disney.Characters.Service.impl.PersonajeService {

    @Autowired
    private PersonajeMap personajeMap;
    @Autowired
    private PersonajeRespository personajeRespository;
    @Autowired
    private PersonajeSpecification personajeSpecification;

    //PROPOSITO:
    //Guarda una entidad en el repositorio. nos devuelve el dto
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity entity = personajeMap.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = personajeRespository.save(entity);
        PersonajeDTO personajeDTO = personajeMap.personajeEntity2DTO(entitySaved, false);
        return personajeDTO;
    }

    //PROPOSITO:
    //Busca en el repositorio una entidad, si no la encuentra devuelve un dto nulo y si la encuentra devuelve un dto con los datos de la entidad
    @Override
    public boolean search(Long id) {

        Boolean existePersonaje;
        if (personajeRespository.existsById(id)) {
            existePersonaje = true;
        } else {
            existePersonaje = false;
        }
        return existePersonaje;
    }


    //PROPOSITO:
    //Genera un update con el id de una entidad y un dto, devuelve la entidad actualizada como dto
    @Override
    public PersonajeEntity update(PersonajeDTO dto, Long id) {
        PersonajeEntity entity = personajeMap.personajeDTO2Entity(dto);
        entity.setId(id);
        personajeRespository.save(entity);

        return entity;
    }

    //PROPOSITO:
    //Borra una entidad por su numero id
    @Override
    public void delete(Long id) {
        personajeRespository.deleteById(id);
    }

    @Override
    public List<PersonajeDTO> buscarPorFiltros(String nombre, Integer edad, Set<Long> idPeliculas) {
        PersonajeFiltrosDTO filtroDTO = new PersonajeFiltrosDTO(nombre, edad, idPeliculas);
        List<PersonajeEntity> entidades = personajeRespository.findAll(personajeSpecification.buscarPorFiltros(filtroDTO));
        List<PersonajeDTO> dtos = personajeMap.personajeEntityList2DTOList(entidades, true);
        return dtos;
    }


}






