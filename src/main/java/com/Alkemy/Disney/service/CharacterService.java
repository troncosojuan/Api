package com.Alkemy.Disney.service;

import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.dto.CharacterDTOBasic;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    CharacterDTO update(CharacterDTO dto, Long id);

    void delete(Long id);

    List<CharacterDTOBasic> searchCharacterByFilters(String nombre, Integer edad, Set<Long> idPelicula);
}
