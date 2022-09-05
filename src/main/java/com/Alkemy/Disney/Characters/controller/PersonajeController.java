package com.Alkemy.Disney.Characters.controller;

import com.Alkemy.Disney.Characters.Service.PersonajeServiceImpl;
import com.Alkemy.Disney.Characters.dto.PersonajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    public PersonajeServiceImpl personajeServiceImpl;

    @PostMapping
    public ResponseEntity<PersonajeDTO> guardar(@RequestBody PersonajeDTO personaje) {
        PersonajeDTO personajeCreado = personajeServiceImpl.save(personaje);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personajeCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrar(@PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (personajeServiceImpl.search(id)) {
            personajeServiceImpl.delete(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> actualizar(@RequestBody PersonajeDTO dto, @PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (personajeServiceImpl.search(id)) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(personajeServiceImpl.update(dto, id), HttpStatus.OK);

        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> buscarPersonajePorFiltros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Set<Long> idPelicula
    ){
        List<PersonajeDTO> personajes = this.personajeServiceImpl.buscarPorFiltros(nombre, edad, idPelicula);
        return ResponseEntity.ok(personajes);
    }



}
