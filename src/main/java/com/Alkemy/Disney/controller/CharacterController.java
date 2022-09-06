package com.Alkemy.Disney.controller;

import com.Alkemy.Disney.Service.CharacterServiceImpl;
import com.Alkemy.Disney.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/personajes")
public class CharacterController {

    @Autowired
    public CharacterServiceImpl characterServiceImpl;

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterCreated = characterServiceImpl.save(character);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (characterServiceImpl.search(id)) {
            characterServiceImpl.delete(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO dto, @PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (characterServiceImpl.search(id)) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(characterServiceImpl.update(dto, id), HttpStatus.OK);

        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> searchCharacterByFilters(@RequestParam(required = false) String nombre, @RequestParam(required = false) Integer edad, @RequestParam(required = false) Set<Long> idPelicula) {
        List<CharacterDTO> personajes = this.characterServiceImpl.searchCharacterByFilters(nombre, edad, idPelicula);
        return ResponseEntity.ok(personajes);
    }


}
