package com.kafeneio.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.model.RawMaterials;

@Repository
public class RawMaterialDAO {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<RawMaterials> fetchRawMaterials() {
		Query query = entityManager.createQuery("select rawMaterial from RawMaterials rawMaterial");
		List <RawMaterials> rawMaterial = query.getResultList();
		return rawMaterial;
	}
}
