package com.kafeneio.service;

import javax.inject.Inject;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.xml.internal.fastinfoset.sax.Properties;
import com.kafeneio.DTO.ParentChildDTO;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.repository.ParentChildRepository;
@Service
@Transactional
public class ParentChildServiceImpl implements ParentChildService {
	@Inject
	ParentChildRepository parentChildRepository;

	@Override
	public void saveParentChild(ParentChildDTO parentChild) {
		ParentChildModel parentChildModel = new ParentChildModel();
		BeanUtils.copyProperties(parentChild, parentChildModel);
	    parentChildRepository.save(parentChildModel);
	}
	@Override
	public ParentChildModel findOneByEmail(String email){
		ParentChildModel parentChildModel=parentChildRepository.findOneByEmail(email);
		return parentChildModel;
		
	}

}
