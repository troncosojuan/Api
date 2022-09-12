package com.Alkemy.Disney.controller;

import com.Alkemy.Disney.dto.CharacterDTO;
import com.Alkemy.Disney.dto.CharacterDTOBasic;
import com.Alkemy.Disney.service.impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/personajes")
public class CharacterController {

    @Autowired
    public CharacterServiceImpl characterServiceImpl;

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO character) {
        CharacterDTO characterCreated = characterServiceImpl.save(character);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterCreated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        characterServiceImpl.delete(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@Valid @RequestBody CharacterDTO dto, @PathVariable("id") Long id) {
        CharacterDTO character = characterServiceImpl.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(character);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTOBasic>> searchCharacterByFilters(@RequestParam(required = false) String nombre, @RequestParam(required = false) Integer edad, @RequestParam(required = false) Set<Long> idPelicula) {
        List<CharacterDTOBasic> personajes = this.characterServiceImpl.searchCharacterByFilters(nombre, edad, idPelicula);
        return ResponseEntity.ok(personajes);
    }


}
