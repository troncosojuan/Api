package com.Alkemy.Disney.Characters.controller;


import com.Alkemy.Disney.Characters.Service.PeliculaServiceImpl;
import com.Alkemy.Disney.Characters.dto.PeliculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    public PeliculaServiceImpl peliculaServiceImpl;

    @PostMapping
    public ResponseEntity<PeliculaDTO> guardar(@RequestBody PeliculaDTO pelicula) {
        PeliculaDTO peliculaCreada = peliculaServiceImpl.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaCreada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrar(@PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (peliculaServiceImpl.search(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            peliculaServiceImpl.delete(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }


    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizar(@RequestBody PeliculaDTO dto, @PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (peliculaServiceImpl.search(id) == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity(peliculaServiceImpl.update(dto, id), HttpStatus.OK);

        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> buscarPersonajePorFiltros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Set<Long> idGenero,
            @RequestParam(required = false) String order
    ){
        List<PeliculaDTO> peliculas = this.peliculaServiceImpl.buscarPorFiltros(titulo, idGenero, order);
        return ResponseEntity.ok(peliculas);
    }



}
