package com.kafeneio.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.Expenses;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Units;
import com.kafeneio.service.UnitsService;


@RestController
public class UnitsController {
	
	@Inject
	UnitsService unitsService;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/addUnits", method = RequestMethod.POST)
	public MessageDTO saveUnits(@RequestBody List<Units> units)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = new MessageDTO();
		try{
			msgDTO =unitsService.saveUnits(units);
		}
		catch(Exception exception){
			msgDTO.setMessage("Some error occured on server!");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
	
	
	
	@RequestMapping(value="/searchUnits",method=RequestMethod.GET)
	public List<Units> getUnits()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<Units> units = unitsService.editUnits();
		return units;

}
	
	
	@RequestMapping(value = "/updateUnit", method = RequestMethod.POST)
	public MessageDTO updateUnit(@RequestBody Units unit)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		MessageDTO msgDTO = unitsService.updateUnit(unit);
		return msgDTO;
	}

}


@Controller
class UnitsLoaderController{

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/units")
	public String units(ModelMap modelMap)
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		Units units = new Units();
		//units.setId(0L);
		modelMap.put("units", units);

		return "units";
	}
}
 
