package com.Alkemy.Disney.Characters.Service.impl;

import com.Alkemy.Disney.Characters.dto.PersonajeDTO;
import com.Alkemy.Disney.Characters.entity.PersonajeEntity;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    PersonajeEntity update(PersonajeDTO dto, Long id);

    void delete(Long id);

    PersonajeDTO search(Long id);


    List<PersonajeDTO> buscarPorFiltros(String nombre, int edad, Set<Long> idPelicula);
}
