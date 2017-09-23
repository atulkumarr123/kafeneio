package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.FoodItems;

public interface FoodItemsRepository  extends JpaRepository<FoodItems, Long> {

}
