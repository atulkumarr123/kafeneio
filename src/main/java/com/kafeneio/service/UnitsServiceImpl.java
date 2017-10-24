package com.kafeneio.service;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.Units;
import com.kafeneio.repository.UnitsRepository;

@Service
public class UnitsServiceImpl extends BaseServiceImpl implements UnitsService{
	@Inject
	UnitsRepository unitsRepository;
	
	@Override
	public MessageDTO saveUnits(List<Units> units) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			for(Units unit : units){
				unit.setDate(new Date());
			}
			unitsRepository.save(units);
			msgDTO.setMessage("Unit(s) added successfully");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Error");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
	}
	
}
