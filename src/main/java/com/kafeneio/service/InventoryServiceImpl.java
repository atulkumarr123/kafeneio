package com.kafeneio.service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.InventoryDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.enums.AppConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.InventoryRules;
import com.kafeneio.model.Order;
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
		List<InventoryRules> inventoryList = new ArrayList<InventoryRules>();
		inventoryDtoList.forEach(inventoryDto -> {
			InventoryRules inventory = new InventoryRules();
			InventoryRules inventoryItem = transformDtoToModel(inventoryDto, inventory);
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


	private InventoryRules transformDtoToModel(InventoryDto inventoryDto, InventoryRules inventory) {
		inventory.setCreationDate(new Date());
		inventory.setLastUpdatedDate(new Date());
		inventory.setQuantity(inventoryDto.getQuantity());
		/*Units unit = unitRepository.findOne(inventoryDto.getUnitsId());
		inventory.setUnit(unit);
		*/RawMaterials rawMaterial = rawMaterialRepository.findOne(inventoryDto.getRawMaterialId());
		inventory.setRawMaterial(rawMaterial);
		FoodItems foodItem = foodItemsRepository.findOne(inventoryDto.getFoodItemsId());
		inventory.setFoodItems(foodItem);
		
		
		inventory.setRemarks(inventoryDto.getRemarks());
		return inventory;
	}
	
	@Override
	public List<RawMaterialDto> fetchRawMaterial(RawMaterialDto rawMaterialSearch) {
		List<RawMaterials> rawMaterialsList = null;
		List<RawMaterialDto> dtoList = new ArrayList<RawMaterialDto>();
		rawMaterialsList = rawMaterialDAO.fetchRawMaterials(rawMaterialSearch);
		rawMaterialsList.forEach(rawMaterial -> {
			RawMaterialDto rawMaterialDto = new RawMaterialDto();
			rawMaterialDto.setCreationDate(rawMaterial.getCreationDate());
			rawMaterialDto.setLastUpdatedDate(rawMaterial.getLastUpdatedDate());
			rawMaterialDto.setLowerLimit(rawMaterial.getLowerLimit().toString());
			rawMaterialDto.setQuantity(rawMaterial.getQuantity().toString());
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
	public List<InventoryDto> fetchInventory(Long foodItemId, Long rawMaterialId) {
		List<InventoryRules> inventoryList = null;
		List<InventoryDto> dtoList = new ArrayList<InventoryDto>();
		inventoryList = inventoryDao.fetchInventory(foodItemId,rawMaterialId);
		inventoryList.forEach(inventory -> {
			InventoryDto inventoryDto = new InventoryDto();
			inventoryDto.setQuantity(inventory.getQuantity());
			inventoryDto.setRemarks(inventory.getRemarks());
			/*inventoryDto.setUnitsId(inventory.getUnit().getId());
			inventoryDto.setUnitsDesc(inventory.getUnit().getDescription());
			*/inventoryDto.setFoodItemsId(inventory.getFoodItems().getId());
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
	public MessageDTO updateInventory(InventoryDto inventoryDto) {
		MessageDTO msgDTO = new MessageDTO();
		try{			
			InventoryRules inventory = inventoryRepository.findOne(inventoryDto.getId());

			
			transformDtoToModelInv(inventoryDto, inventory);			
			inventoryRepository.save(inventory);
			msgDTO.setMessage("Inventory Updated");
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
	public MessageDTO deleteInvRule(Long id) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			inventoryRepository.delete(id);
			msgDTO.setMessage("Inventory Rule deleted");
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
		rawMaterial.setLowerLimit(rawMaterialDto.getLowerLimit()!=null && !rawMaterialDto.getLowerLimit().isEmpty()?
				new BigDecimal(rawMaterialDto.getLowerLimit()):null);
		rawMaterial.setQuantity(rawMaterialDto.getQuantity()!=null && !rawMaterialDto.getQuantity().isEmpty()?
				new BigDecimal(rawMaterialDto.getQuantity()):null);
		rawMaterial.setRawMaterialCode(rawMaterialDto.getRawMaterialCode());
		rawMaterial.setRawMaterialDesc(rawMaterialDto.getRawMaterialDesc());
		Units unit = unitRepository.findOne(rawMaterialDto.getUnitValue());
		rawMaterial.setUnit(unit);
		rawMaterial.setRemarks(rawMaterial.getRemarks());
		return rawMaterial;
	}
	
	
	private InventoryRules transformDtoToModelInv(InventoryDto inventoryDto, InventoryRules inventory) {
		inventory.setQuantity(inventoryDto.getQuantity());
		FoodItems foodItems = foodItemsRepository.findOne(inventoryDto.getFoodItemsId());
		inventory.setFoodItems(foodItems);
		RawMaterials rawMaterial = rawMaterialRepository.findOne(inventoryDto.getRawMaterialId());
		inventory.setRawMaterial(rawMaterial);
		/*Units unit = unitRepository.findOne(inventoryDto.getUnitsId());
		inventory.setUnit(unit);
		*/inventory.setRemarks(inventoryDto.getRemarks());
		return inventory;
	}
	@Override
	public List<MessageDTO> settleInventory(Order order,String mode) throws KafeneioException{
		List<MessageDTO> msgDTOList = new ArrayList<MessageDTO>();
		// 1. get the objects of orderDetails.	
		// 2. Loop over on this list.
		try{
			order.getOrderDetails().forEach(orderDetail ->{
				// 3. get food item code.
				String code = orderDetail.getFoodCode();
				// 4. fetch food item object.

				FoodItems foodItem = foodItemsRepository.findByFoodItemCode(code);
				// 5. fetch rules corresponding to this food item.
				List<InventoryRules> rules = inventoryRepository.findByFoodItems(foodItem);
				// 6. Loop over on the list of rules.
				rules.forEach(rule ->{
					// 7. Fetch raw material object based on the rule id of each rule.
					RawMaterials rawMaterial = rule.getRawMaterial();
					// 8. Change the quantity of Raw Material decreasing it by the defined quantity in rule object.
					int numberOfItems = orderDetail.getQuantity().intValue();
					BigDecimal quantityToAdjust = rule.getQuantity().multiply(new BigDecimal(numberOfItems));
					if(ApplicationConstant.SUBTRACT.equalsIgnoreCase(mode)){
						rawMaterial.setQuantity(rawMaterial.getQuantity().subtract(quantityToAdjust));
					}
					else if(ApplicationConstant.ADD.equalsIgnoreCase(mode)){
						rawMaterial.setQuantity(rawMaterial.getQuantity().add(quantityToAdjust));
					}
					rawMaterialRepository.save(rawMaterial);
					if(rawMaterial.getQuantity().compareTo(rawMaterial.getLowerLimit()) == -1 || rawMaterial.getQuantity().compareTo(rawMaterial.getLowerLimit()) == 0){
						MessageDTO msgDTO = new MessageDTO();
						msgDTO.setMessage(rawMaterial.getRawMaterialDesc() +" "+rawMaterial.getQuantity() +" "+ rawMaterial.getUnit().getDescription());
						msgDTO.setStatusCode(HttpStatus.OK.value());
						msgDTO.setMessageType(AppConstant.INFO);
						msgDTOList.add(msgDTO);
					}
				});

			});
		}
		catch(Exception e){
			/*MessageDTO msgDTO = new MessageDTO();
			msgDTO.setMessage("Some Error Occured While Reducing the Inventory");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			msgDTO.setMessageType(AppConstant.ERROR);
			msgDTOList.add(msgDTO);*/
			throw new KafeneioException("Some Error Occured While Reducing the Inventory",e);
		}
		return msgDTOList;	
	}


	@Override
	public List<RawMaterials> fetchRawMaterials() {
		List<RawMaterials> rawMaterialsList = rawMaterialDAO.fetchRawMaterialList();
		return rawMaterialsList;
		
	}
}
