package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.InventoryDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.enums.AppConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Order;
import com.kafeneio.model.RawMaterials;

public interface InventoryService extends BaseService{
	
	public MessageDTO saveInventoryItems(List<InventoryDto> inventoryItems);
	
	public List<RawMaterialDto> fetchRawMaterial(RawMaterialDto rawMaterial);

	public List<InventoryDto> fetchInventory(Long foodItemId, Long rawMaterialId);

	public MessageDTO updateRawMaterial(RawMaterialDto rawMaterialDto);

	public MessageDTO updateInventory(InventoryDto inventoryDto);
	
	public MessageDTO saveRawMaterials(List<RawMaterialDto> rawMaterials);

	MessageDTO deleteRawMaterial(Long id);

	MessageDTO deleteInvRule(Long id);
	
	public List<MessageDTO> settleInventory(Order order,String mode) throws KafeneioException;

	public List<RawMaterials> fetchRawMaterials();

}
