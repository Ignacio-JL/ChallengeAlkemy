 package com.Alkemy.Challenge.repository.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.Alkemy.Challenge.dto.CharacterFiltersDTO;
import com.Alkemy.Challenge.entity.CharacterEntity;
import com.Alkemy.Challenge.entity.MovieEntity;

@Component
public class CharacterSpecification {

	public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {

		return (root, query, criteriaBuilder) ->{
			List<Predicate> predicates = new ArrayList<>();
			
			if(StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
							criteriaBuilder.like(
									criteriaBuilder.lower(root.get("name")), 
									"%" + filtersDTO.getName().toLowerCase() + "%"
						)
				);
			}
			
			if(StringUtils.hasLength(filtersDTO.getAge())) {
				predicates.add(criteriaBuilder.equal(root.get("age"), filtersDTO.getAge()));
			}
			
			if(!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
				Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
				Expression<String> citiesId = join.get("id");
				predicates.add(citiesId.in(filtersDTO.getMovies()));
			}
			
			query.distinct(true);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			
		};
	}

}
