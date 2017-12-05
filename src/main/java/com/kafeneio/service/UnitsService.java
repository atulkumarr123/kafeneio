package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Units;

public interface UnitsService {
	
	public MessageDTO saveUnits(List<Units> units);
	List<Units> editUnits();
	public MessageDTO updateUnit(Units unit);
}
