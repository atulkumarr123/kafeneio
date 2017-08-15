package com.kafeneio.service;

import java.util.List;

import com.kafeneio.DTO.LinkDto;
import com.kafeneio.DTO.WorkAssignmentDTO;

public interface WorkAssignmentService {
List<LinkDto> getTodayTaskService(Long userId);
int updateStatus(WorkAssignmentDTO workAssigment);
}
