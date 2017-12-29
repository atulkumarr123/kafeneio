package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.model.RawMaterials;

public interface InventoryService {
	
	public MessageDTO saveRawMaterials(List<RawMaterialDto> rawMaterials);
	
	public List<RawMaterialDto> fetchRawMaterial();

	public MessageDTO updateRawMaterial(RawMaterialDto rawMaterialDto);

}
