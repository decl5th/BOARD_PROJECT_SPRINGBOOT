package org.choongang.farm.repositories;

import org.choongang.farm.entities.FarmTourplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FarmTourPlaceRepository extends JpaRepository<FarmTourplace, Long>, QuerydslPredicateExecutor<FarmTourplace> {
}
