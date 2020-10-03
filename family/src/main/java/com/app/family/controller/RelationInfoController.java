package com.app.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.service.PersonalInfoService;
import com.app.family.service.RelationInfoService;

@RestController
@RequestMapping("/familyRelation") 
public class RelationInfoController {
	
	@Autowired
	RelationInfoService service;
	
	@Autowired
	PersonalInfoService pInfoservice;
	
	
	
	
}
