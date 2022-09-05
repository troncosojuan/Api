package com.Alkemy.Disney.Characters.Repository;

import com.Alkemy.Disney.Characters.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRespository extends JpaRepository<PersonajeEntity, Long>, JpaSpecificationExecutor<PersonajeEntity> {

    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);
}
