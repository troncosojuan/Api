package com.Alkemy.Disney.Characters.Service.impl;

import com.Alkemy.Disney.Characters.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);

    List<GeneroDTO> allGeneros();
}

