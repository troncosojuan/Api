package com.Alkemy.Disney.controller;


import com.Alkemy.Disney.dto.MovieDTO;
import com.Alkemy.Disney.dto.MovieDTOBasic;
import com.Alkemy.Disney.repository.MovieRepository;
import com.Alkemy.Disney.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;


    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movie) {
        MovieDTO movieCreated = movieServiceImpl.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        movieServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @RequestBody MovieDTO dto, @PathVariable("id") Long id) {
        MovieDTO movieDTO = movieServiceImpl.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(movieDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTOBasic>> searchMovieByFilter(@RequestParam(required = false) String title,
                                                                   @RequestParam(required = false) Set<Long> idGender,
                                                                   @RequestParam(required = false, defaultValue = "ASC") String order) {
        List<MovieDTOBasic> movies = this.movieServiceImpl.searchByFilter(title, idGender, order);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity addCharacter2Movie(@PathVariable("idMovie") Long idMovie, @PathVariable("idCharacter") Long idCharacter) {
        movieServiceImpl.addCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity deleteCharacter2Movie(@PathVariable("idMovie") Long idMovie, @PathVariable("idCharacter") Long idCharacter) {
        movieServiceImpl.deleteCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
