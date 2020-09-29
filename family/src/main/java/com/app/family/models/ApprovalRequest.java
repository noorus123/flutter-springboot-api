package com.app.family.models;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.app.family.enums.JoinFamilyStatus;

@Document
public class ApprovalRequest {
	
	@Id
	private String _id;
	private String approvalId;
	private String memberId;
	private String memberName;
	private String familyId;
	private String familyName;
	private String familySide;
	private String familyType;
	private String adminId;
	private String requestStatus=JoinFamilyStatus.PENDING.getText();
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}	
	public String getApprovalId() {
		return approvalId;
	}
	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
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
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}	
	public String getFamilySide() {
		return familySide;
	}
	public void setFamilySide(String familySide) {
		this.familySide = familySide;
	}
	public String getFamilyType() {
		return familyType;
	}
	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}
	@Override
	public String toString() {
		return "ApprovalRequest [_id=" + _id + ", approvalId=" + approvalId + ", memberId=" + memberId + ", memberName="
				+ memberName + ", familyId=" + familyId + ", familyName=" + familyName + ", familySide=" + familySide
				+ ", familyType=" + familyType + ", adminId=" + adminId + ", requestStatus=" + requestStatus + "]";
	}	
}
