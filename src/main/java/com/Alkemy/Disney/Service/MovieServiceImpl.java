package com.Alkemy.Disney.Service;

import com.Alkemy.Disney.Repository.MovieRepository;
import com.Alkemy.Disney.Repository.specifications.MovieSpecification;
import com.Alkemy.Disney.dto.MovieDTO;
import com.Alkemy.Disney.dto.MovieFiltersDTO;
import com.Alkemy.Disney.entity.MovieEntity;
import com.Alkemy.Disney.mapper.MovieMap;
import com.Alkemy.Disney.Service.impl.MovieService;
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


    //PROPOSITO: Guarda una pelicula con sus personajes asociados y su genero en el repositorio
    //Parametro: dto de pelicula
    //Salida: dto de pelicula
    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMap.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO movieDTO = movieMap.movieEntity2DTO(entitySaved, true);
        return movieDTO;
    }


    //PROPOSITO: Actualiza datos basicos de una pelicula en el repositorio
    //Parametro: dto de pelicula a actualizar, id de pelicula a actualizar
    //Salida: si encuentra la pelicula previamente creada, devuelve la pelicula actualizada, caso contrario devuelve null
    @Override
    public MovieDTO update(MovieDTO dto, Long id) {
        MovieDTO movieDTO;
        if (search(id)) {
            MovieEntity movieEntity = movieMap.movieUpdatedDTO2Entity(dto, id);
            MovieEntity movieUpdated = movieRepository.save(movieEntity);
            movieDTO = movieMap.movieEntity2DTO(movieUpdated, true);
        } else {
            movieDTO = null;
        }
        return movieDTO;

    }

    //PROPOSITO: borra una pelicula del repositorio
    //parametro: id de pelicula
    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    //PROPOSITO: busca una pelicula en el repositorio por su id
    //parametro: id de pelicula a buscar
    //Salida: boolean
    @Override
    public boolean search(Long id) {
        Boolean movieExist;
        if (movieRepository.existsById(id)) {
            movieExist = true;
        } else {
            movieExist = false;
        }
        return movieExist;
    }

    //Proposito: busca peliculas por filtro, si no se agregan filtros devuelve la lista completa de peliculas
    //parametros: String titulo, Set<Long> id, String orden
    //salida: lista de peliculas
    @Override
    public List<MovieDTO> searchByFilter(String titulo, Set<Long> idGender, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(titulo, idGender, order);
        List<MovieEntity> entities = movieRepository.findAll(MovieSpecification.searchByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMap.movieEntityCollection2DTOList(entities, true);
        return dtos;
    }
}


