package com.Alkemy.Disney.Characters.Repository.specifications;


import com.Alkemy.Disney.Characters.dto.PersonajeFiltrosDTO;
import com.Alkemy.Disney.Characters.entity.PeliculaEntity;
import com.Alkemy.Disney.Characters.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonajeSpecification {

    public Specification<PersonajeEntity> buscarPorFiltros(PersonajeFiltrosDTO filtrosDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtrosDTO.getNombre())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtrosDTO.getNombre().toLowerCase() + "%"
                        )
                );
            }


            if (filtrosDTO.getEdad() != null && filtrosDTO.getEdad() > 0) {
                predicates.add(
                        criteriaBuilder.equal(root.get("edad"),
                                filtrosDTO.getEdad()
                        )
                );
            }

            if (!CollectionUtils.isEmpty(filtrosDTO.getIdPeliculas())) {
                Join<PeliculaEntity, PersonajeEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filtrosDTO.getIdPeliculas()));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };
    }
}