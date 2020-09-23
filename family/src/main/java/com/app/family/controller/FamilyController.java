package com.app.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.models.Family;
import com.app.family.models.FamilyDTO;
import com.app.family.service.FamilyService;

@RestController
@RequestMapping("/familyAccount") 
public class FamilyController {
	
	@Autowired
	FamilyService service;
	
	@RequestMapping(value = "/addFamilyDTO", method = RequestMethod.POST)
	public Family addFamilyDTO(@RequestBody FamilyDTO dto) {
		System.out.println("executing ::: addFamilyDTO");
		Family family = null;		
		try {			
			family = service.processFamilyDTO(dto);			
		}catch (Exception e) {
			System.out.println("Failed adding family account .... "+ e.getMessage());	
		}
		return family;
	}
	
	
}
