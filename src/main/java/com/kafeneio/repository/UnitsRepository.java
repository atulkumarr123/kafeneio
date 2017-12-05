package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Units;

public interface UnitsRepository  extends JpaRepository<Units , Long> {

	@Query(value="select units  from Units units ")
	List<Units> editUnits();
}
