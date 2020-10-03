package com.app.family.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.family.dao.RepositoryService;
import com.app.family.service.RelationInfoService;

@Service
public class RelationInfoServiceImpl implements RelationInfoService{
	
	@Autowired
	RepositoryService repositoryService;	
	
	
}
