package com.kafeneio.controller.inventory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.FoodItemsDto;
import com.kafeneio.DTO.InventoryDto;
import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Units;
import com.kafeneio.service.FoodItemsService;
import com.kafeneio.service.InventoryService;
import com.kafeneio.service.UnitsService;


@RestController
public class InventoryController {


	@Inject
	InventoryService inventoryService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/saveInventoryItems", method = RequestMethod.POST)
	public MessageDTO saveInventoryItems(@RequestBody List<InventoryDto> inventoryItems)
			throws KafeneioException, com.kafeneio.exception.BadRequestException{
		MessageDTO msgDTO = inventoryService.saveInventoryItems(inventoryItems);
		return msgDTO;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/saveRawMaterials", method = RequestMethod.POST)
	public MessageDTO saveRawMaterials(@RequestBody List<RawMaterialDto> rawMaterials)
			throws KafeneioException, com.kafeneio.exception.BadRequestException{
		MessageDTO msgDTO = inventoryService.saveRawMaterials(rawMaterials);
		return msgDTO;
	}
	
	@RequestMapping(value = "/rawMaterialList")
	public List<RawMaterialDto> fetchRawMaterials() {
		List<RawMaterialDto> rawMaterials = inventoryService.fetchRawMaterial(); 
		return rawMaterials;
	}
	
	@RequestMapping(value = "/inventoryList")
	public List<InventoryDto> fetchInventory() {
		List<InventoryDto> inventoryDtoList= inventoryService.fetchInventory(); 
		return inventoryDtoList;
	}
	
	@RequestMapping(value = "/updateRawMaterial", method = RequestMethod.POST)
	public MessageDTO updateRawMaterial(@RequestBody RawMaterialDto rawMaterialDto)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = inventoryService.updateRawMaterial(rawMaterialDto);
		return msgDTO;
	}
	
	@RequestMapping(value = "/updateInventory", method = RequestMethod.POST)
	public MessageDTO updateInventory(@RequestBody InventoryDto inventoryDto)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = inventoryService.updateInventory(inventoryDto);
		return msgDTO;
	}
	
	@RequestMapping(value = "/deleteRawMaterial/{id}")
	public MessageDTO deleteRawMaterial(@PathVariable Long id) {
		MessageDTO msgDTO = inventoryService.deleteRawMaterial(id); 
		return msgDTO ;
	}
	

}


@Controller
class InventoryLoaderController{
	
	@Inject
	UnitsService unitsService;
	

	@Inject
	InventoryService inventoryService;
	
	@Inject
	FoodItemsService foodItemsService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/rawMaterial")
	public String salesAnalytics(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		modelMap.put("dateTimeFormat", ApplicationConstant.DATE_TIME_FORMAT);
		modelMap.put("dateTimeFormatCalendar", ApplicationConstant.DATE_TIME_FORMAT_CALENDAR);
		//List<ModeOfPayment> modeOfPayments = new ArrayList<ModeOfPayment>();
		List<Units> unitList = unitsService.findUnits();
		modelMap.put("unitList",unitList);
		Units units = new Units();
		modelMap.put("rawMaterial", units);
		SimpleDateFormat dateFormat = new SimpleDateFormat(ApplicationConstant.DATE_FORMAT);
		String fromDate = dateFormat.format(new Date());
		String toDate = getTomorrow(dateFormat);
		fromDate = fromDate + " 12:00 AM";
		toDate = toDate + " 12:00 AM";
		modelMap.put("fromDateTime", fromDate);
		modelMap.put("toDateTime", toDate);
		String url = "/inventory/rawMaterial"; 
		return url;
	}
	private String getTomorrow(SimpleDateFormat dateFormat) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1); // to get previous year add -1
		Date nextDay = cal.getTime();
		String tomorrow = dateFormat.format(nextDay);
		return tomorrow;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/inventoryRules")
	public String inventory(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<Units> unitList = unitsService.findUnits();
		modelMap.put("unitList",unitList);
		Units units = new Units();
		modelMap.put("inventory", units);
		List<RawMaterialDto> rawMaterialsList = inventoryService.fetchRawMaterial();
		modelMap.put("rawMaterialList",rawMaterialsList );
		RawMaterialDto rawMaterial = new RawMaterialDto();
		modelMap.put("inventory", rawMaterial);
		
		List<FoodItemsDto> foodItemsList = foodItemsService.fetchFoodItems();
		modelMap.put("foodItemsList",foodItemsList );
		FoodItemsDto foodItems = new FoodItemsDto();
		modelMap.put("inventory", foodItems);
		
		String url = "/inventory/inventory"; 
		return url;
	}
}