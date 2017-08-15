package com.kafeneio.service;

import java.util.List;

import org.json.JSONArray;

import com.kafeneio.DTO.GenerateBinaryTreeDTO;
import com.kafeneio.model.ParentChildModel;

public interface DashboardService {
	List<GenerateBinaryTreeDTO>  generateBinaryTree(Long parentId);
}
