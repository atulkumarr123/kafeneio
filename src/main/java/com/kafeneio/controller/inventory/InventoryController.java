package com.kafeneio.controller.inventory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.constants.ApplicationConstant;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.RawMaterials;
import com.kafeneio.model.Units;
import com.kafeneio.service.InventoryService;
import com.kafeneio.service.UnitsService;


@RestController
public class InventoryController {


	@Inject
	InventoryService inventoryService;
	
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
	
	@RequestMapping(value = "/updateRawMaterial", method = RequestMethod.POST)
	public MessageDTO updateRawMaterial(@RequestBody RawMaterialDto rawMaterialDto)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = inventoryService.updateRawMaterial(rawMaterialDto);
		return msgDTO;
	}
	

}


@Controller
class InventoryLoaderController{
	
	@Inject
	UnitsService unitsService;
	
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
}