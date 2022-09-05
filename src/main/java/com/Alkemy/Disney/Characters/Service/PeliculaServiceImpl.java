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

//    @Autowired
//    public PeliculaServiceImpl(PeliculaMap peliculaMap, PeliculaRepository peliculaRepository, PeliculaSpecification peliculaSpecification) {
//        this.peliculaMap = peliculaMap;
//        this.peliculaRepository = peliculaRepository;
//        this.peliculaSpecification = peliculaSpecification;
//    }


    @Override
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = peliculaMap.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO peliculaDTO = peliculaMap.peliculaEntity2DTO(entitySaved, true);
        return peliculaDTO;
    }

    @Override
    public PeliculaDTO update(PeliculaDTO dto, Long id) {
        PeliculaEntity entity = peliculaMap.peliculaDTO2Entity(dto);
        entity.setId(id);
        peliculaRepository.save(entity);
        dto.setId(id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO search(Long id) {
        PeliculaDTO peliculaDTO;
        if (!peliculaRepository.existsById(id)) {
            peliculaDTO = null;
        } else {
            PeliculaEntity peliculaEncontrado = peliculaRepository.findById(id).get();
            peliculaDTO = peliculaMap.peliculaEntity2DTO(peliculaEncontrado, true);
        }
        return peliculaDTO;
    }

    @Override
    public List<PeliculaDTO> buscarPorFiltros(String titulo, Set<Long> idGenero, String order) {
        PeliculaFiltrosDTO filtroDTO = new PeliculaFiltrosDTO(titulo, idGenero, order);
        List<PeliculaEntity> entidades = peliculaRepository.findAll(peliculaSpecification.buscarPorFiltros(filtroDTO));
        List<PeliculaDTO> dtos = peliculaMap.peliculaEntityList2DTOList(entidades, true);
        return dtos;
    }
}


