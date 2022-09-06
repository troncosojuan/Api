package com.Alkemy.Disney.Repository;


import com.Alkemy.Disney.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
}
