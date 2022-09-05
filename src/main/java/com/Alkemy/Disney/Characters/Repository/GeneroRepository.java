package com.Alkemy.Disney.Characters.Repository;


import com.Alkemy.Disney.Characters.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroEntity, Long> {
}
