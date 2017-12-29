package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;

public interface EditFoodItemsRepository  extends JpaRepository<FoodItems, Long> {
	
}
