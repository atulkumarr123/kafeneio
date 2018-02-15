/**
 * 
 */
package com.kafeneio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafeneio.model.Inventory;

/**
 * @author palak
 * 
 *
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	

}
