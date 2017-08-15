package com.kafeneio.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.kafeneio.DTO.GenerateBinaryTreeDTO;
import com.kafeneio.model.ParentChildModel;
import com.kafeneio.repository.ParentChildRepository;

@Service
public class DashboardServiceImpl implements DashboardService {
//	JSONArray jsonArray = new JSONArray();
	List<GenerateBinaryTreeDTO>generateBinaryTreeDTOList;
	@Inject
	ParentChildRepository parentChildRepository;
	public List<GenerateBinaryTreeDTO> generateBinaryTree(Long parentId){
		generateBinaryTreeDTOList=new ArrayList<>();
		ParentChildModel parentChildModel= parentChildRepository.findByRegistration(parentId);
		/*JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", parentChildModel.getRegistration().getId());
		jsonObject.put("Name", parentChildModel.getRegistration().getName());
		jsonObject.put("parent", parentChildModel.getParentId());
		jsonArray.put(0, jsonObject);*/
		GenerateBinaryTreeDTO generateBinaryTreeDTO=new GenerateBinaryTreeDTO();
		generateBinaryTreeDTO.setId(parentChildModel.getRegistration().getId());
		generateBinaryTreeDTO.setName(parentChildModel.getRegistration().getName());
		generateBinaryTreeDTO.setParent(0L);
		generateBinaryTreeDTOList.add(generateBinaryTreeDTO);
		generateBinaryTreeDTOList=getChildNodes(parentChildModel.getRegistration().getId());
		
		return generateBinaryTreeDTOList;
	}
	
	private List<GenerateBinaryTreeDTO> getChildNodes(Long parentId){
		
		
		
	String abc	=parentChildRepository.findByParentId(parentId);
	
	
	List<String> myList = new ArrayList<>(Arrays.asList(abc.split(",")));
	/*for(int i=0;i<childNodesList.size();i++){
		GenerateBinaryTreeDTO generateBinaryTreeDTO=new GenerateBinaryTreeDTO();
		generateBinaryTreeDTO.setId(childNodesList.get(i).getRegistration().getId());
		generateBinaryTreeDTO.setName(childNodesList.get(i).getRegistration().getName());
		generateBinaryTreeDTO.setParent(childNodesList.get(i).getParentId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", childNodesList.get(i).getRegistration().getId());
		jsonObject.put("Name", childNodesList.get(i).getRegistration().getName());
		jsonObject.put("parent", childNodesList.get(i).getParentId());
		jsonArray.put(j, jsonObject);
		j++;
		
		generateBinaryTreeDTOList.add(generateBinaryTreeDTO);
		
		
	}
	
	
	//JSONObject jsonObject = new JSONObject();
	
	
		return generateBinaryTreeDTOList;*/
	List<Long>lst=new ArrayList<>();
	
	for(int i=0;i<myList.size();i++){
		
		ParentChildModel parentChildModel=parentChildRepository.findByRegistration(Long.parseLong((myList.get(i))));
		GenerateBinaryTreeDTO generateBinaryTreeDTO=new GenerateBinaryTreeDTO();
		
		
		generateBinaryTreeDTO.setId(parentChildModel.getRegistration().getId());
		generateBinaryTreeDTO.setName(parentChildModel.getRegistration().getName());
		generateBinaryTreeDTO.setParent(parentChildModel.getParentId());
		generateBinaryTreeDTOList.add(generateBinaryTreeDTO);
		
		
		lst.add(Long.parseLong((myList.get(i))));
	}
	/*List<ParentChildModel> parentChildModel=parentChildRepository.findByRegistrationIn(lst);
	for(int i=0;i<parentChildModel.size();i++)
	{
        GenerateBinaryTreeDTO generateBinaryTreeDTO=new GenerateBinaryTreeDTO();
		generateBinaryTreeDTO.setId(parentChildModel.get(i).getRegistration().getId());
		generateBinaryTreeDTO.setName(parentChildModel.get(i).getRegistration().getName());
		generateBinaryTreeDTO.setParent(parentChildModel.get(i).getParentId());
		generateBinaryTreeDTOList.add(generateBinaryTreeDTO);
	}*/
	return generateBinaryTreeDTOList;
	}
}
