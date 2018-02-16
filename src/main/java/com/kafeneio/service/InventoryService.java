package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.InventoryDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;

public interface InventoryService extends BaseService{
	
	public MessageDTO saveInventoryItems(List<InventoryDto> inventoryItems);
	
	public List<RawMaterialDto> fetchRawMaterial();

	public List<InventoryDto> fetchInventory();

	public MessageDTO updateRawMaterial(RawMaterialDto rawMaterialDto);

	public MessageDTO updateInventory(InventoryDto inventoryDto);
	
	public MessageDTO saveRawMaterials(List<RawMaterialDto> rawMaterials);

	MessageDTO deleteRawMaterial(Long id);

}
