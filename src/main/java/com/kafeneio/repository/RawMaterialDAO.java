package com.kafeneio.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.model.RawMaterials;

@Repository
public class RawMaterialDAO {
	
	@Inject
	EntityManager entityManager;
	
	
	public List<RawMaterials> fetchRawMaterials(RawMaterialDto rawMat) {
		StringBuffer queryStr = new StringBuffer("select rawMaterial from RawMaterials rawMaterial ");
		if(rawMat.getUnitValue()!=null){
			queryStr.append(" where rawMaterial.unit.id ="+rawMat.getUnitValue());
		}
		if(rawMat.getRawMaterialCode()!=null
				&& !rawMat.getRawMaterialCode().isEmpty()){
			queryStr.append(" and rawMaterial.rawMaterialCode like '%"+rawMat.getRawMaterialCode()+"%'");
		}
		
		if( rawMat.getRawMaterialDesc()!=null 
				&& !rawMat.getRawMaterialDesc().isEmpty()){
			queryStr.append(" and rawMaterial.rawMaterialDesc like '%"+rawMat.getRawMaterialDesc()+"%'");
		}

		/*if((rawMat.getRawMaterialCode()!=null
				&& !rawMat.getRawMaterialCode().isEmpty()) && (rawMat.getUnitValue()!=null 
				&& rawMat.getUnitValue() != 0)){
			queryStr.append(" and rawMaterial.unit = "+rawMat.getUnitValue());
		}*/

		if( rawMat.getQuantity()!=null 
				&& !rawMat.getQuantity().isEmpty()){
			queryStr.append(" and rawMaterial.quantity like '%"+rawMat.getQuantity()+"%'");
		}
		
		if( rawMat.getLowerLimit()!=null 
				&& !rawMat.getLowerLimit().isEmpty()){
			queryStr.append(" and rawMaterial.lowerLimit ="+rawMat.getLowerLimit());
		}

		if( rawMat.getRemarks()!=null 
				&& !rawMat.getRemarks().isEmpty()){
			queryStr.append(" and rawMaterial.remarks like '%"+rawMat.getRemarks()+"%'");
		}

		Query query = entityManager.createQuery(queryStr.toString());


		List <RawMaterials> rawMaterial = query.getResultList();
		return rawMaterial;
	}


	public List<RawMaterials> fetchRawMaterialList() {
		Query query = entityManager.createQuery("Select rawMaterial from RawMaterials rawMaterial order by id desc");
		List <RawMaterials> rawMaterials = query.getResultList();
		return rawMaterials;
	}
}
