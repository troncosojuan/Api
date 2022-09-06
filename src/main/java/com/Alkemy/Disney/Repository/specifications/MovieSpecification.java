package com.Alkemy.Disney.Repository.specifications;

import com.Alkemy.Disney.dto.MovieFiltersDTO;
import com.Alkemy.Disney.entity.GenderEntity;
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
public class MovieSpecification {

    public Specification<MovieEntity> searchByFilters(MovieFiltersDTO filtroDTO) {
        return (root, query, criteriaBuilder) -> {


            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtroDTO.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + filtroDTO.getTitulo().toLowerCase() + "%"
                        )
                );
            }

            if (!CollectionUtils.isEmpty(filtroDTO.getIdGenero())) {
                Join<GenderEntity, MovieEntity> join = root.join("generos", JoinType.INNER);
                Expression<String> generoId = join.get("id");
                predicates.add(generoId.in(filtroDTO.getIdGenero()));
            }
            query.distinct(true);


            String orderByField = "titulo";
            query.orderBy(
                    filtroDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };


    }
}
