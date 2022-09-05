package com.Alkemy.Disney.Characters.Repository.specifications;

import com.Alkemy.Disney.Characters.dto.PeliculaFiltrosDTO;
import com.Alkemy.Disney.Characters.entity.GeneroEntity;
import com.Alkemy.Disney.Characters.entity.PeliculaEntity;
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
public class PeliculaSpecification {

    public Specification<PeliculaEntity> buscarPorFiltros(PeliculaFiltrosDTO filtroDTO) {
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
                Join<GeneroEntity, PeliculaEntity> join = root.join("generos", JoinType.INNER);
                Expression<String> generoId = join.get("id");
                predicates.add(generoId.in(filtroDTO.getIdGenero()));
            }
            query.distinct(true);

            String orderByField = "order";
            query.orderBy(
                    filtroDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        };


    }
}
