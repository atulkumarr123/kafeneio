package com.kafeneio.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.InventoryRules;
import com.kafeneio.model.RawMaterials;

@Repository
public class InventoryDao {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<InventoryRules> fetchInventory(Long foodItemId, Long rawMaterialId) {
		
		StringBuffer queryStr = new StringBuffer("select inventoryRules from InventoryRules inventoryRules");
		if(foodItemId !=null  && foodItemId != 0){
			queryStr.append(" where inventoryRules.foodItems.id="+foodItemId);
			}
		if((foodItemId !=null  && foodItemId != 0)&&(rawMaterialId !=null && rawMaterialId != 0)){
			queryStr.append(" and inventoryRules.rawMaterial.id="+rawMaterialId);
		}
		else if(rawMaterialId !=null && rawMaterialId != 0){
			queryStr.append(" where inventoryRules.rawMaterial.id="+rawMaterialId);
		}
		Query query = entityManager.createQuery(queryStr.toString());
		/*if(!(foodItemId ==null)  && !(foodItemId == 0)){
				query.setParameter("id", foodItemId);
		}
		
		if(!(rawMaterialId ==null) && !(rawMaterialId == 0)){
			query.setParameter("id", rawMaterialId);
		}*/
		List <InventoryRules> inventory = query.getResultList();
		return inventory;
	}
}
