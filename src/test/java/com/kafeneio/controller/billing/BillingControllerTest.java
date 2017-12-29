package com.kafeneio.controller.billing;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kafeneio.DTO.MessageDTO;
import com.kafeneio.DTO.RawMaterialDto;
import com.kafeneio.configuration.AppConfiguration;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodItems;
import com.kafeneio.model.Units;
import com.kafeneio.service.BillingService;
import com.kafeneio.service.FoodService;
import com.kafeneio.service.InventoryService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
public class BillingControllerTest {
	@Inject
	@Qualifier("foodService")
	FoodService foodService;
	@Inject
	BillingService billingService;
	
	@Inject
	InventoryService inventoryService;
	

	@Test
	public void getFoods()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<FoodItems> items = foodService.findFoodItems("PL");
		items.forEach((item)->System.out.println(item.getFoodItemDesc()));
		assertTrue(items.size()>0);
	}
	
	@Test
	public void saveRawMaterials(){
		
		List<RawMaterialDto> rawMaterials = new ArrayList<RawMaterialDto>();
		RawMaterialDto rawMaterialDto = new RawMaterialDto();
		rawMaterialDto.setCreationDate(new Date());
		rawMaterialDto.setLowerLimit(new BigDecimal(5));
		rawMaterialDto.setQuantity(new BigDecimal(5));
		rawMaterialDto.setRawMaterialCode("BRD");
//		rawMaterialDto.setUnit(new Units());		

		RawMaterialDto rawMaterialDto1 = new RawMaterialDto();
		rawMaterialDto.setCreationDate(new Date());
		rawMaterialDto.setLowerLimit(new BigDecimal(6));
		rawMaterialDto.setQuantity(new BigDecimal(6));
		rawMaterialDto.setRawMaterialCode("BUN");

		rawMaterials.add(rawMaterialDto);
		rawMaterials.add(rawMaterialDto1);
		
		MessageDTO msgDTO = inventoryService.saveRawMaterials(rawMaterials);
		
	}


}
