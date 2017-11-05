package com.kafeneio.controller.billing;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kafeneio.configuration.AppConfiguration;
import com.kafeneio.exception.KafeneioException;
import com.kafeneio.model.FoodItems;
import com.kafeneio.service.BillingService;
import com.kafeneio.service.FoodService;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class, loader = AnnotationConfigWebContextLoader.class)
public class BillingControllerTest {
	@Inject
	@Qualifier("foodService")
	FoodService foodService;
	@Inject
	BillingService billingService;

	@Test
	public void getFoods()
			throws KafeneioException, com.kafeneio.exception.BadRequestException {
		List<FoodItems> items = foodService.findFoodItems("PL");
		items.forEach((item)->System.out.println(item.getFoodItemDesc()));
		assertTrue(items.size()>0);
	}


}
