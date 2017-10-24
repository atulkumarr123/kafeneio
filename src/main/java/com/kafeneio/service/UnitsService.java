package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Units;

public interface UnitsService {
	
	public MessageDTO saveUnits(List<Units> units);
}
