package com.app.family.service;

import java.util.List;

import com.app.family.models.ApprovalRequest;
import com.app.family.models.Family;
import com.app.family.models.FamilyDTO;

public interface FamilyService {

	public Family processFamilyDTO(FamilyDTO dto);

	public ApprovalRequest createApprovalRequest(ApprovalRequest request);

	public Family verifyRequestGetFamilyByFamilyCode(String text, String memberId);

	public List<ApprovalRequest> getApprovalRequestByAdminId(String adminId);

	public ApprovalRequest approveRejectFamilyJoinRequest(ApprovalRequest request);
	
	
	
	


}
