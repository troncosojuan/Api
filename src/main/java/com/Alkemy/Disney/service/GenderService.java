package com.Alkemy.Disney.service;

import com.Alkemy.Disney.dto.GenderDTO;

import java.util.List;

public interface GenderService {

    GenderDTO save(GenderDTO dto);

    List<GenderDTO> allGeneros();
}

