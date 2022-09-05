package com.Alkemy.Disney.Characters.controller;

import com.Alkemy.Disney.Characters.Service.GeneroServiceImpl;
import com.Alkemy.Disney.Characters.dto.GeneroDTO;
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
    public GeneroServiceImpl generoService;


    @PostMapping("/id")
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero) {
        GeneroDTO generoCreado = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(generoCreado);
    }


}
