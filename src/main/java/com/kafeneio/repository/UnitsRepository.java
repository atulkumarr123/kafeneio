package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Units;

public interface UnitsRepository  extends JpaRepository<Units , Long> {

}
