package com.kafeneio.service;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.model.FoodCategory;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Order;
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
				unit.setCreationDate(new Date());
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

	@Override
	public List<Units> editUnits() {
		List<Units> items =   unitsRepository.editUnits();
		return items;
	}

	@Override
	public MessageDTO updateUnit(Units unit) {
		MessageDTO msgDTO = new MessageDTO();
		try{
			Units unitFromDb = unitsRepository.findOne(unit.getId());
			unitFromDb.setCode(unit.getCode());
			unitFromDb.setDescription(unit.getDescription());
			unitFromDb.setStatus(unit.isStatus());
			
			unitsRepository.save(unitFromDb);
			msgDTO.setMessage("Unit Updated");
			msgDTO.setStatusCode(HttpStatus.OK.value());
		}
		catch(Exception exception){
			msgDTO.setMessage("Some Error Occured");
			msgDTO.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return msgDTO;
		
	}
	
	@Override
	public List<Units> findUnits() {
		List<Units> units = unitsRepository.findUnits();
		/*Map<String,String> foodCatMap=new HashMap<String,String>();
		Iterator<FoodCategory> itr=categories.iterator();
		if(itr.hasNext()){
			FoodCategory foodCategory = itr.next();
			foodCatMap.put(foodCategory.getFoodCode(),foodCategory.getFoodDesc());	
		}*/
		
		
		return units;
	}

	
}
