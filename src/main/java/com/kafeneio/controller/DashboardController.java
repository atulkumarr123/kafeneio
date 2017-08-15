package com.kafeneio.controller;

import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.GenerateBinaryTreeDTO;
import com.kafeneio.exception.BadRequestException;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.service.DashboardService;
import com.kafeneio.enums.Codes;
import com.kafeneio.enums.ResponseKeyName;

@RestController

public class DashboardController extends BaseRestController {
@Inject
DashboardService dashboardService;
	@RequestMapping(value = "/generateTree", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSponsorName(
		      @RequestParam(value = "parentId", required = true) Long parentId) throws BadRequestException {
	
		List<GenerateBinaryTreeDTO> binaryTreeNode=	dashboardService.generateBinaryTree(parentId);
		
		return ResponseEntity.ok(createSuccessResponse(ResponseKeyName.BINARYTREE, binaryTreeNode));
		
		
	}

}
