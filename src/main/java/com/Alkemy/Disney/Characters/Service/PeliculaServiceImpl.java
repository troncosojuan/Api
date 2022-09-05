package com.Alkemy.Disney.Characters.Service;

import com.Alkemy.Disney.Characters.Repository.PeliculaRepository;
import com.Alkemy.Disney.Characters.Repository.specifications.PeliculaSpecification;
import com.Alkemy.Disney.Characters.dto.PeliculaDTO;
import com.Alkemy.Disney.Characters.dto.PeliculaFiltrosDTO;
import com.Alkemy.Disney.Characters.entity.PeliculaEntity;
import com.Alkemy.Disney.Characters.mapper.PeliculaMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements com.Alkemy.Disney.Characters.Service.impl.PeliculaService {

    @Autowired
    private PeliculaMap peliculaMap;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PeliculaSpecification peliculaSpecification;



    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = peliculaMap.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO peliculaDTO = peliculaMap.peliculaEntity2DTO(entitySaved, true);
        return peliculaDTO;
    }

    @Override
    public PeliculaDTO update(PeliculaDTO dto, Long id) {
        PeliculaDTO peliculaDTO;
        if (search(id)) {
            PeliculaEntity peliculaEntity = peliculaMap.peliculaActualizadaDTO2Entity(dto, id);
            PeliculaEntity peliculaActualizada = peliculaRepository.save(peliculaEntity);
            peliculaDTO = peliculaMap.peliculaEntity2DTO(peliculaActualizada, true);
        } else {
            peliculaDTO = null;
        }
        return peliculaDTO;

    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public boolean search(Long id) {
        Boolean existePelicula;
        if (peliculaRepository.existsById(id)) {
            existePelicula = true;
        } else {
            existePelicula = false;
        }
        return existePelicula;
    }

    @Override
    public List<PeliculaDTO> buscarPorFiltros(String titulo, Set<Long> idGenero, String order) {
        PeliculaFiltrosDTO filtroDTO = new PeliculaFiltrosDTO(titulo, idGenero, order);
        List<PeliculaEntity> entidades = peliculaRepository.findAll(peliculaSpecification.buscarPorFiltros(filtroDTO));
        List<PeliculaDTO> dtos = peliculaMap.peliculaEntityList2DTOList(entidades, true);
        return dtos;
    }
}


