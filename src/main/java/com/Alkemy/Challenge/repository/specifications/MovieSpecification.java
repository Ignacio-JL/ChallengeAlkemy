package com.Alkemy.Challenge.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.Alkemy.Challenge.dto.MovieFiltersDTO;
import com.Alkemy.Challenge.entity.GenderEntity;
import com.Alkemy.Challenge.entity.MovieEntity;

@Component
public class MovieSpecification {
	public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO){
		
		return (root, query, criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(criteriaBuilder.like(
									criteriaBuilder.lower(root.get("title")), 
									"%" + filtersDTO.getName().toLowerCase() + "%"
						)
					);
			}
			
			if(StringUtils.hasLength(filtersDTO.getGender())) {
				//"gender" -> atributo de moviefilterDTO
				Join<GenderEntity, MovieEntity> join = root.join("gender",JoinType.INNER);
				Expression<String> genderId = join.get("id");
				predicates.add(genderId.in(filtersDTO.getGender()));
			}
			
			query.distinct(true);
			
			String orderBy = "title";
			//Por defecto sera descendente
			query.orderBy(
					filtersDTO.isDESC()?
							criteriaBuilder.desc(root.get(orderBy)) :
							criteriaBuilder.asc(root.get(orderBy))
					);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
		}

}
