package com.Alkemy.Disney.controller;


import com.Alkemy.Disney.Repository.MovieRepository;
import com.Alkemy.Disney.Service.MovieServiceImpl;
import com.Alkemy.Disney.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieServiceImpl movieServiceImpl;
    @Autowired
    private MovieRepository movieRepository;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieCreated = movieServiceImpl.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (movieServiceImpl.search(id)) {
            movieServiceImpl.delete(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO dto, @PathVariable("id") Long id) {
        ResponseEntity response = null;

        if (movieServiceImpl.search(id)) {
            response = new ResponseEntity(movieServiceImpl.update(dto, id), HttpStatus.OK);
        } else {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> searchMovieByFilter(@RequestParam(required = false) String title,
                                                              @RequestParam(required = false) Set<Long> idGender,
                                                              @RequestParam(required = false, defaultValue = "ASC") String order) {
        List<MovieDTO> movies = this.movieServiceImpl.searchByFilter(title, idGender, order);
        return ResponseEntity.ok(movies);
    }


}
