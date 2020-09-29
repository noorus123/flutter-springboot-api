package com.app.family.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.family.models.ApprovalRequest;
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
	
	@RequestMapping(value="/getFamilyAccount/{familyCode}/{memberId}", method = RequestMethod.GET)
    public Family checkRequestGetFamily(@PathVariable(value = "familyCode") String familyCode, @PathVariable(value = "memberId") String memberId) {
		System.out.println("executing ::: checkRequestGetFamily");
		Family family = service.verifyRequestGetFamilyByFamilyCode(familyCode,memberId);
		return family!=null ? family : null; 		
	}
	
	@RequestMapping(value = "/sendApprovalRequest", method = RequestMethod.POST)
	public ApprovalRequest createApprovalRequest(@RequestBody ApprovalRequest request) {
		System.out.println("executing ::: createApprovalRequest");
		ApprovalRequest req = null;		
		try {			
			req = service.createApprovalRequest(request);			
		}catch (Exception e) {
			System.out.println("Failed sending approval request .... "+ e.getMessage());	
		}
		return req;
	}
	
	@RequestMapping(value="/getApprovalRequestList/{adminId}", method = RequestMethod.GET)
    public List<ApprovalRequest> getApprovalRequestList(@PathVariable(value = "adminId") String adminId) {
		System.out.println("executing ::: getApprovalRequestList");
		List<ApprovalRequest> requestList = service.getApprovalRequestByAdminId(adminId);
		return (!CollectionUtils.isEmpty(requestList))  ? requestList : null; 		
	}
	
	@RequestMapping(value="/processApprovalRequest", method = RequestMethod.POST)
    public ApprovalRequest processApprovalRequest(@RequestBody ApprovalRequest request) {
		System.out.println("executing ::: processApprovalRequest");
		ApprovalRequest req = service.approveRejectFamilyJoinRequest(request);
		return (req!=null)  ? req : null; 		
	}
	
	
	
}
