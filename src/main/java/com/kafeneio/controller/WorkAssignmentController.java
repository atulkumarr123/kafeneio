package com.kafeneio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafeneio.DTO.GenerateBinaryTreeDTO;
import com.kafeneio.DTO.LinkDto;
import com.kafeneio.DTO.WorkAssignmentDTO;
import com.kafeneio.exception.BadRequestException;
import com.kafeneio.service.WorkAssignmentService;
import com.kafeneio.enums.ResponseKeyName;

@RestController
public class WorkAssignmentController extends BaseRestController{
	
	@Autowired
	WorkAssignmentService workAssignmentService;
	
	@RequestMapping(value = "/todayTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getTodayTask( @RequestParam(value = "userId", required = true) Long userId) throws BadRequestException {
	
		
		List<LinkDto> linkDtolist =workAssignmentService.getTodayTaskService(userId);
		return ResponseEntity.ok(createSuccessResponse(ResponseKeyName.TODAY_TASK, linkDtolist));
	}
	
	@RequestMapping(value = "/updateStatus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateStause(@RequestBody WorkAssignmentDTO workAssignment) throws BadRequestException {
		workAssignmentService.updateStatus(workAssignment);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
