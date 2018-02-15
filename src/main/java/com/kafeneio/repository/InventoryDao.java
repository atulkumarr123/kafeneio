package com.kafeneio.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.Inventory;
import com.kafeneio.model.RawMaterials;

@Repository
public class InventoryDao {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<Inventory> fetchInventory() {
		Query query = entityManager.createQuery("select inventory from Inventory inventory order by inventory.id desc");
		List <Inventory> inventory = query.getResultList();
		return inventory;
	}
}
