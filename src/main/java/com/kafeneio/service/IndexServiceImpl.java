package com.kafeneio.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kafeneio.model.ConfigParameters;
import com.kafeneio.repository.ConfigParametersRepo;

@Service
@Qualifier(value="indexService")
public class IndexServiceImpl extends BaseServiceImpl implements IndexService{	
	
	@Inject
	ConfigParametersRepo configParamRepo;
	
	@Override
	public Map<String,String> getConfigParameters() {
		List<ConfigParameters> paramters = configParamRepo.findAll();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramters.forEach(param->{paramMap.put(param.getKey(), param.getValue());});
		return paramMap;
	}
	
}
