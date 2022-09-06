package com.Alkemy.Disney.Service.impl;

import com.Alkemy.Disney.dto.GenderDTO;

import java.util.List;

public interface GenderService {

    GenderDTO save(GenderDTO dto);

    List<GenderDTO> allGeneros();
}

