package com.kafeneio.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.InventoryDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Inventory;
import com.kafeneio.model.RawMaterials;
import com.kafeneio.model.Units;
import com.kafeneio.repository.FoodItemsRepository;
import com.kafeneio.repository.InventoryDao;
import com.kafeneio.repository.InventoryRepository;
import com.kafeneio.repository.RawMaterialDAO;
import com.kafeneio.repository.RawMaterialRepository;
import com.kafeneio.repository.UnitsRepository;

@Service
public class InventoryServiceImpl extends BaseServiceImpl implements InventoryService{
	
/*	@Inject
	InventoryRepository inventoryRepository;*/
	
	@Inject
	RawMaterialRepository rawMaterialRepository;
	
	@Inject
	UnitsRepository unitRepository;
	
	@Inject
	RawMaterialDAO rawMaterialDAO;
	
	@Inject
	InventoryDao inventoryDao;

	@Inject
	FoodItemsRepository foodItemsRepository;
	
	@Inject
	InventoryRepository inventoryRepository;
	
	@Override
	public MessageDTO saveInventoryItems(List<InventoryDto> inventoryDtoList) {
		MessageDTO msgDTO = new MessageDTO();
		try{
		List<Inventory> inventoryList = new ArrayList<Inventory>();
		inventoryDtoList.forEach(inventoryDto -> {
			Inventory inventory = new Inventory();
			Inventory inventoryItem = transformDtoToModel(inventoryDto, inventory);
			inventoryList.add(inventoryItem);	
		});
			inventoryRepository.save(inventoryList);
			msgDTO.setMessage("Inventory Item Saved Successfully");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
		
	}


	private Inventory transformDtoToModel(InventoryDto inventoryDto, Inventory inventory) {
		inventory.setCreationDate(new Date());
		inventory.setLastUpdatedDate(new Date());
		inventory.setQuantity(inventoryDto.getQuantity());
		Units unit = unitRepository.findOne(inventoryDto.getUnitsId());
		inventory.setUnit(unit);
		RawMaterials rawMaterial = rawMaterialRepository.findOne(inventoryDto.getRawMaterialId());
		inventory.setRawMaterial(rawMaterial);
		FoodItems foodItem = foodItemsRepository.findOne(inventoryDto.getFoodItemsId());
		inventory.setFoodItems(foodItem);
		
		
		inventory.setRemarks(inventoryDto.getRemarks());
		return inventory;
	}
	
	@Override
	public List<RawMaterialDto> fetchRawMaterial() {
		List<RawMaterials> rawMaterialsList = null;
		List<RawMaterialDto> dtoList = new ArrayList<RawMaterialDto>();
		rawMaterialsList = rawMaterialDAO.fetchRawMaterials();
		rawMaterialsList.forEach(rawMaterial -> {
			RawMaterialDto rawMaterialDto = new RawMaterialDto();
			rawMaterialDto.setCreationDate(rawMaterial.getCreationDate());
			rawMaterialDto.setLastUpdatedDate(rawMaterial.getLastUpdatedDate());
			rawMaterialDto.setLowerLimit(rawMaterial.getLowerLimit());
			rawMaterialDto.setQuantity(rawMaterial.getQuantity());
			rawMaterialDto.setRawMaterialCode(rawMaterial.getRawMaterialCode());
			rawMaterialDto.setRawMaterialDesc(rawMaterial.getRawMaterialDesc());
			rawMaterialDto.setRemarks(rawMaterial.getRemarks());
			rawMaterialDto.setUnitDesc(rawMaterial.getUnit().getDescription());
			rawMaterialDto.setUnitValue(rawMaterial.getUnit().getId());
			rawMaterialDto.setId(rawMaterial.getId());
			dtoList.add(rawMaterialDto);		
		});
		return dtoList;
	}
	
	
	@Override
	public List<InventoryDto> fetchInventory() {
		List<Inventory> inventoryList = null;
		List<InventoryDto> dtoList = new ArrayList<InventoryDto>();
		inventoryList = inventoryDao.fetchInventory();
		inventoryList.forEach(inventory -> {
			InventoryDto inventoryDto = new InventoryDto();
			inventoryDto.setQuantity(inventory.getQuantity());
			inventoryDto.setRemarks(inventory.getRemarks());
			inventoryDto.setUnitsId(inventory.getUnit().getId());
			inventoryDto.setUnitsDesc(inventory.getUnit().getDescription());
			inventoryDto.setFoodItemsId(inventory.getFoodItems().getId());
			inventoryDto.setFoodItemsDesc(inventory.getFoodItems().getFoodItemDesc());
			inventoryDto.setRawMaterialId(inventory.getRawMaterial().getId());
			inventoryDto.setRawMaterialDesc(inventory.getRawMaterial().getRawMaterialDesc());
			
			inventoryDto.setId(inventory.getId());
			dtoList.add(inventoryDto);		
		});
		return dtoList;
	}
	
	@Override
	public MessageDTO updateRawMaterial(RawMaterialDto rawMaterialDto) {
		MessageDTO msgDTO = new MessageDTO();
		try{			
			RawMaterials rawMaterial = rawMaterialRepository.findOne(rawMaterialDto.getId());
			transformDtoToModel(rawMaterialDto, rawMaterial);			
			rawMaterialRepository.save(rawMaterial);
			msgDTO.setMessage("Raw Material Updated");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;	
	}
	
	@Override
	public MessageDTO deleteRawMaterial(Long id) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			rawMaterialRepository.delete(id);
			msgDTO.setMessage("Raw Material deleted");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured During Deletion");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
	
	@Override
	public MessageDTO saveRawMaterials(List<RawMaterialDto> rawMaterialDtoList) {
		MessageDTO msgDTO = new MessageDTO();
		try{
		List<RawMaterials> rawMaterialsList = new ArrayList<RawMaterials>();
		rawMaterialDtoList.forEach(rawMaterialDto -> {
			RawMaterials rawMaterial = new RawMaterials();
			RawMaterials rawMaterials = transformDtoToModel(rawMaterialDto, rawMaterial);

			rawMaterialsList.add(rawMaterials);	
		});
			rawMaterialRepository.save(rawMaterialsList);
			msgDTO.setMessage("Raw Materials Saved Successfully");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
		
	}

	private RawMaterials transformDtoToModel(RawMaterialDto rawMaterialDto, RawMaterials rawMaterial) {
		rawMaterial.setCreationDate(new Date());
		rawMaterial.setLastUpdatedDate(new Date());
		rawMaterial.setLowerLimit(rawMaterialDto.getLowerLimit());
		rawMaterial.setQuantity(rawMaterialDto.getQuantity());
		rawMaterial.setRawMaterialCode(rawMaterialDto.getRawMaterialCode());
		rawMaterial.setRawMaterialDesc(rawMaterialDto.getRawMaterialDesc());
		Units unit = unitRepository.findOne(rawMaterialDto.getUnitValue());
		rawMaterial.setUnit(unit);
		rawMaterial.setRemarks(rawMaterial.getRemarks());
		return rawMaterial;
	}
}
