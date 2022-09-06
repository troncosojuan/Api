package com.Alkemy.Disney.Service.impl;

import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    CharacterEntity update(CharacterDTO dto, Long id);

    void delete(Long id);

    boolean search(Long id);


    List<CharacterDTO> searchCharacterByFilters(String nombre, Integer edad, Set<Long> idPelicula);
}
