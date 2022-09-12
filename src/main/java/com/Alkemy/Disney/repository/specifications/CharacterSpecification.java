package com.Alkemy.Disney.repository.specifications;


import com.Alkemy.Disney.dto.CharacterFiltersDTO;
import com.Alkemy.Disney.entity.CharacterEntity;
import com.Alkemy.Disney.entity.MovieEntity;
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
public class CharacterSpecification {

    public Specification<CharacterEntity> searchByFilters(CharacterFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getNombre())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtersDTO.getNombre().toLowerCase() + "%"
                        )
                );
            }


            if (filtersDTO.getEdad() != null && filtersDTO.getEdad() > 0) {
                predicates.add(
                        criteriaBuilder.equal(root.get("edad"),
                                filtersDTO.getEdad()
                        )
                );
            }

            if (!CollectionUtils.isEmpty(filtersDTO.getIdPeliculas())) {
                Join<MovieEntity, CharacterEntity> join = root.join("peliculas", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getIdPeliculas()));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };
    }
}