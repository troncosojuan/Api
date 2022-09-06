package com.Alkemy.Disney.Service.impl;

import com.Alkemy.Disney.dto.MovieDTO;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    MovieDTO update(MovieDTO dto, Long id);

    void delete(Long id);

    boolean search(Long id);


    List<MovieDTO> searchByFilter(String title, Set<Long> idGender, String order);
}
