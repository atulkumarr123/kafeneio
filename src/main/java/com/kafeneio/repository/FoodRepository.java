package com.kafeneio.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;

public interface FoodRepository  extends JpaRepository<FoodCategory, Long> {
	
	@Query(value="select foodCategory  from FoodCategory foodCategory where foodCategory.status= :status")
	List<FoodCategory> findFoodCategory(@Param("status")String status);
	
	FoodCategory findById(Long id);
	
	
	@Query(value="select foodItems  from FoodItems foodItems where foodItems.foodCategory.foodCode= :foodCode and foodItems.status= 1")
	List<FoodItems> findFoodItemsForCategory(@Param("foodCode")String foodCode);
	
	@Query(value="select foodItems  from FoodItems foodItems where foodItems.status= 1")
	List<FoodItems> fetchFoodItems();
	
	
	@Query(value="select case when testParent.toDate>testParent.fromDate then 1 else 0 end from TestParent testParent")
	List<Object> switchCaseDemo();
}
