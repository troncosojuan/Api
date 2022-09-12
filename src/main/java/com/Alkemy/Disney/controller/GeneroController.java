package com.Alkemy.Disney.controller;

import com.Alkemy.Disney.service.impl.GenderServiceImpl;
import com.Alkemy.Disney.dto.GenderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    public GenderServiceImpl generoService;


    @PostMapping("/id")
    public ResponseEntity<GenderDTO> save(@RequestBody GenderDTO genero) {
        GenderDTO genderCreated = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genderCreated);
    }


}
