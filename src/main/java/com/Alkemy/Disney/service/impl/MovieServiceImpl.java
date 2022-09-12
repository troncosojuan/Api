package com.Alkemy.Disney.service.impl;

import com.Alkemy.Disney.dto.MovieDTO;
import com.Alkemy.Disney.dto.MovieDTOBasic;
import com.Alkemy.Disney.dto.MovieFiltersDTO;
import com.Alkemy.Disney.entity.CharacterEntity;
import com.Alkemy.Disney.entity.MovieEntity;
import com.Alkemy.Disney.exception.ParamNotFound;
import com.Alkemy.Disney.mapper.MovieMap;
import com.Alkemy.Disney.repository.CharacterRespository;
import com.Alkemy.Disney.repository.MovieRepository;
import com.Alkemy.Disney.repository.specifications.MovieSpecification;
import com.Alkemy.Disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMap movieMap;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieSpecification MovieSpecification;
    @Autowired
    private CharacterRespository characterRespository;


    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMap.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO movieDTO = movieMap.movieEntity2DTO(entitySaved, true);
        return movieDTO;
    }


    @Override
    public MovieDTO update(MovieDTO dto, Long id) {
        MovieDTO movieDTO;
        if (movieRepository.existsById(id)) {
            MovieEntity movieEntity = movieMap.movieUpdatedDTO2Entity(dto, id);
            MovieEntity movieUpdated = movieRepository.save(movieEntity);
            movieDTO = movieMap.movieEntity2DTO(movieUpdated, true);
        } else {
            throw new ParamNotFound("Id Movie no valido");
        }
        return movieDTO;

    }


    @Override
    public void delete(Long id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new ParamNotFound("Id movie not exist");
        }
    }


    @Override
    public List<MovieDTOBasic> searchByFilter(String titulo, Set<Long> idGender, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(titulo, idGender, order);
        List<MovieEntity> entities = movieRepository.findAll(MovieSpecification.searchByFilters(filtersDTO));
        List<MovieDTOBasic> dtos = movieMap.movieEntityCollection2BasicDTOList(entities);
        return dtos;
    }

    @Override
    public void addCharacter(Long idMovie, Long idCharacter) {
        CharacterEntity characterEntity = characterRespository.findById(idCharacter).orElseThrow(() -> new ParamNotFound("idMovie or idCharacter not valid"));
        MovieEntity movieEntity = movieRepository.findById(idMovie).orElseThrow(() -> new ParamNotFound("idMovie or idCharacter not valid"));
        movieEntity.agregarPersonaje(characterEntity);
        movieRepository.save(movieEntity);

    }


    @Override
    public void deleteCharacter(Long idMovie, Long idCharacter) {
        CharacterEntity characterEntity = characterRespository.findById(idCharacter).orElseThrow(() -> new ParamNotFound("idMovie or idCharacter not valid"));
        MovieEntity movieEntity = movieRepository.findById(idMovie).orElseThrow(() -> new ParamNotFound("idMovie or idCharacter not valid"));
        movieEntity.borrarPersonaje(characterEntity);
        movieRepository.save(movieEntity);
    }


}


