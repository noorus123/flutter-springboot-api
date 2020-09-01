package com.app.family.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.app.family.enums.JoinFamilyStatus;

@Document
public class ApprovalRequests {	
	
	@Id
	private String approvRequestId;
	private String personalId;
	private String familyId;
	private String adminId;
	private JoinFamilyStatus requestStatus;
	
	public ApprovalRequests() {
		super();
	}

	public String getApprovRequestId() {
		return approvRequestId;
	}

	public void setApprovRequestId(String approvRequestId) {
		this.approvRequestId = approvRequestId;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public JoinFamilyStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(JoinFamilyStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
}
