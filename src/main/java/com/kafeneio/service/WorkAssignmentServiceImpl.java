package com.kafeneio.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.kafeneio.DTO.LinkDto;
import com.kafeneio.DTO.WorkAssignmentDTO;
import com.kafeneio.model.LinkModel;
import com.kafeneio.model.RegistrationModel;
import com.kafeneio.model.WorkAssignmentModel;
import com.kafeneio.repository.LinkRepository;
import com.kafeneio.repository.RegistrationRepository;
import com.kafeneio.repository.WorkAssignmentRepository;
import com.kafeneio.utils.AppUtils;
import com.kafeneio.enums.AppConstant;

@Service
@Transactional
public class WorkAssignmentServiceImpl implements WorkAssignmentService {
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	WorkAssignmentRepository workAssignmentRepository;
	@Autowired
	LinkRepository linkRepository;

	@SuppressWarnings("unchecked")
	public List<LinkDto> getTodayTaskService(Long userId) {
		int limit = 0;
		List<LinkModel> linkModelList = new ArrayList<>();
		List<LinkDto> linkDtoList = new ArrayList<>();
		RegistrationModel registrationModel = registrationRepository.findOne(userId);
		String date = AppUtils.createDate();
		List<WorkAssignmentModel> workAssigmentModel = workAssignmentRepository.findByUserIdAndDate(userId, date);

		/* Select Data from Link table */
		if (AppConstant.STP_TEN_STRING.getStringValue().equals(registrationModel.getPlanType())) {

			limit = AppConstant.STP_TEN.getValue();

		}

		if (AppConstant.STP_TWENTY_STRING.getStringValue().equals(registrationModel.getPlanType())) {

			limit = AppConstant.STP_TWENTY.getValue();

		}

		if (AppConstant.STP_FIFTY_STRING.getStringValue().equals(registrationModel.getPlanType())) {

			limit = AppConstant.STP_FIFTY.getValue();

		}

		if (AppConstant.STP_HUNDREAD_STRING.getStringValue().equals(registrationModel.getPlanType())) {

			limit = AppConstant.STP_HUNDREAD.getValue();

		}
		linkModelList = (List<LinkModel>) linkRepository.getByLimit(limit);

		/* Insert data into work assigment table */

		for (int i = 0; i < linkModelList.size(); i++) {
			LinkDto linkDto = new LinkDto();
			if (workAssigmentModel.size() == 0) {
				WorkAssignmentModel workAssignmentModelval = new WorkAssignmentModel();
				workAssignmentModelval.setUserId(userId);
				workAssignmentModelval.setLinkId(linkModelList.get(i).getId());
				workAssignmentModelval.setDate(date);
				workAssignmentModelval.setStatus(false);
				workAssignmentRepository.save(workAssignmentModelval);

			}

			BeanUtils.copyProperties(linkModelList.get(i), linkDto);
			WorkAssignmentModel workAssignmentModel = workAssignmentRepository
					.findBylinkIdAndUserIdAndDate(linkModelList.get(i).getId(), userId, date);
			;
			linkDto.setStatus(workAssignmentModel.getStatus());
			linkDtoList.add(linkDto);

		}

		return linkDtoList;

	}

	public int updateStatus(WorkAssignmentDTO workAssigment) {
		WorkAssignmentDTO workAssigmentDto = new WorkAssignmentDTO();
		String date = AppUtils.createDate();
		int value = workAssignmentRepository.updateStatus(workAssigment.getStatus(), date, workAssigment.getUserId(),
				workAssigment.getLinkId());

		return value;

	}

}
