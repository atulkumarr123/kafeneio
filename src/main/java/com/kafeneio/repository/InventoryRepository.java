/**
 * 
 */
package com.kafeneio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.FoodItems;
import com.kafeneio.model.InventoryRules;

/**
 * @author palak
 * 
 *
 */
public interface InventoryRepository extends JpaRepository<InventoryRules, Long> {

	public List<InventoryRules> findByFoodItems(FoodItems item); 	
	
	

}
