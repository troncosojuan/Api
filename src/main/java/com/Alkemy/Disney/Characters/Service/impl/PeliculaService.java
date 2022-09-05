package com.Alkemy.Disney.Characters.Service.impl;

import com.Alkemy.Disney.Characters.dto.PeliculaDTO;

import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);

    PeliculaDTO update(PeliculaDTO dto, Long id);

    void delete(Long id);

    boolean search(Long id);


    List<PeliculaDTO> buscarPorFiltros(String nombre, Set<Long> idGenero, String order);
}
