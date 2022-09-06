package com.Alkemy.Disney.Repository;

import com.Alkemy.Disney.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRespository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {

    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
}
