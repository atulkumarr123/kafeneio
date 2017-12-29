package com.kafeneio.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.RawMaterials;
import com.kafeneio.model.Units;
import com.kafeneio.repository.RawMaterialDAO;
import com.kafeneio.repository.RawMaterialRepository;
import com.kafeneio.repository.UnitsRepository;

@Service
public class InventoryServiceImpl extends BaseServiceImpl implements InventoryService{
	
	@Inject
	RawMaterialRepository rawMaterialRepository;
	
	@Inject
	UnitsRepository unitRepository;
	
	@Inject
	RawMaterialDAO rawMaterialDAO;

	
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
			
			dtoList.add(rawMaterialDto);
			
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
	
}
