package com.Alkemy.Disney.service;

import com.Alkemy.Disney.dto.MovieDTO;
import com.Alkemy.Disney.dto.MovieDTOBasic;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    MovieDTO update(MovieDTO dto, Long id);

    void delete(Long id);


    List<MovieDTOBasic> searchByFilter(String title, Set<Long> idGender, String order);

    void addCharacter(Long idMovie, Long idCharacter);

    void deleteCharacter(Long idMovie, Long idCharacter);
}
