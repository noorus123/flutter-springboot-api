package com.app.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.models.RelationInfo;
import com.app.family.service.PersonalInfoService;
import com.app.family.service.RelationInfoService;

@RestController
@RequestMapping("/relation") 
public class RelationInfoController {
	
	@Autowired
	RelationInfoService service;
	
	@Autowired
	PersonalInfoService pInfoservice;
	
	@RequestMapping(value = "/addUserRelationInfo", method = RequestMethod.POST)
	public RelationInfo addUserRelationInfo(@RequestBody RelationInfo relInfo) {
		System.out.println("executing ::: addUserRelationInfo");
		RelationInfo usr = null;
		if (relInfo != null) {
			System.out.println("executing ::: addUserRelationInfo with user values " + relInfo.toString());
			try {
				usr = service.saveUserRelationInfo(relInfo);
			} catch (Exception e) {
				System.out.println("Failed adding userRelationInfo .... " + e.getMessage());
			} 
		}
		return usr;
	}
	
	
}
