package com.app.family.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.family.dao.RepositoryService;
import com.app.family.enums.JoinFamilyStatus;
import com.app.family.models.Admin;
import com.app.family.models.ApprovalRequest;
import com.app.family.models.Family;
import com.app.family.models.FamilyDTO;
import com.app.family.models.FamilyMember;
import com.app.family.models.PersonalInfo;
import com.app.family.models.SubscribedFamily;
import com.app.family.service.FamilyService;
import com.app.family.service.PersonalInfoService;
import com.app.family.service.SequenceService;
import com.app.family.utils.DateUtil;

@Service
public class FamilyServiceImpl implements FamilyService {
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	SequenceService sequenceService;
	
	@Autowired
	PersonalInfoService personalInfoService;

	@Override
	public Family processFamilyDTO(FamilyDTO dto) {
		Family f = null;
		if (dto != null) {
			f = createNewFamilyAccount(dto);
			if (f != null) {
				List<SubscribedFamily> sfList = new ArrayList<>();
				List<String> afList = new ArrayList<String>();
				SubscribedFamily sf = new SubscribedFamily();
				sf.setFamilyId(f.getFamilyId());
				sf.setFamilyName(f.getFamilyName());
				sf.setFamilySide(dto.getFamilySide());
				sf.setFamilyType(dto.getFamilyType());
				sfList.add(sf);
				afList.add(f.getFamilyId());
				String id = f.getAdmin().getAdminId();
				PersonalInfo personalInfo = personalInfoService.getUserPersonalInfoByLoginId(id);
				if (personalInfo == null) {
					personalInfo = new PersonalInfo();
					personalInfo.setName(f.getAdmin().getName());
					personalInfo.setPersonalId(f.getAdmin().getAdminId());
					personalInfo.setSubscribedFamilies(sfList);
					personalInfo.setFamiliesAdmin(afList);
				} else {
					List<SubscribedFamily> subscribedFamilies = personalInfo.getSubscribedFamilies();
					if (!CollectionUtils.isEmpty(subscribedFamilies)) {
						subscribedFamilies.addAll(sfList);
					} else {
						subscribedFamilies = new ArrayList<SubscribedFamily>();
						subscribedFamilies.addAll(sfList);
					}
					List<String> adminfamilies = personalInfo.getFamiliesAdmin();
					if (!CollectionUtils.isEmpty(adminfamilies)) {
						adminfamilies.addAll(afList);
					} else {
						adminfamilies = new ArrayList<String>();
						adminfamilies.addAll(afList);
					}
					personalInfo.setSubscribedFamilies(subscribedFamilies);
					personalInfo.setFamiliesAdmin(adminfamilies);
				}
				PersonalInfo u = repositoryService.savePersonalInfo(personalInfo);
				if (u != null) {
					System.out.println("Family Account linked with Admin, PersonalInfo updated successfully " + u.toString());
					ApprovalRequest request = new ApprovalRequest();
					String loginId = dto.getLoginInfo().getLoginId();
					String approvalId = f.getFamilyCode()+"_"+loginId; 
					request.setApprovalId(approvalId);
					request.setRequestStatus(JoinFamilyStatus.APPROVED.getText());
					request.setAdminId(loginId);
					request.setMemberId(loginId);
					request.setMemberName(u.getName());
					request.setFamilySide(dto.getFamilySide());
					request.setFamilyType(dto.getFamilyType());
					request.setFamilyId(f.getFamilyId());
					request.setFamilyName(f.getFamilyName());
					ApprovalRequest req = repositoryService.saveApprovalRequest(request);
					if(req != null) {
						System.out.println("Family Admin Account linked with ApprovalRequest successfully ");
					} else {
						System.out.println("Something went wrong while linking ApprovalRequest of Family Account with Admin ");
					}
				} else {
					System.out.println("Something went wrong while linking of Family Account with Admin PersonalInfo ");
				}
			} else {
				System.out.println("Failed to create new family Account");
			}			
		}else {
			System.out.println("No data provided for creating Family account");
		}
		
		return f;
	}
	
	private Family createNewFamilyAccount(FamilyDTO dto) {
		Admin admin = null;
		FamilyMember member = null;
		Family f = null;
		String id=null;
		if(dto != null) {			
			if(dto.getLoginInfo() != null) {
				id = dto.getLoginInfo().getLoginId();
				admin = new Admin();
				admin.setAdminId(id);
				admin.setName(dto.getLoginInfo().getName());
				
				member = new FamilyMember();
				member.setName(dto.getLoginInfo().getName());
				member.setPersonalId(dto.getLoginInfo().getLoginId());	
			}
			Family family = new Family();
			family.setFamilyName(dto.getFamilyName());
			family.setAdmin(admin);
			
			List<FamilyMember> memberList = new ArrayList<FamilyMember>();
			memberList.add(member);
			family.setFamilyMembers(memberList);
			
			String familyCode = createFamilyCode();
			family.setFamilyCode(familyCode);
			
			String familyId = createFamilyId(familyCode, id);
			family.setFamilyId(familyId);
			
			f = repositoryService.saveFamilyAccount(family);			
		}	
		return f;
	}

	private String createFamilyId(String familyCode, String id) {
		String fId=null;
		if(!StringUtils.isEmpty(familyCode) && !StringUtils.isEmpty(id))
		fId = familyCode+"_"+id;
		return fId;
	}

	private String createFamilyCode() {
		String fcode=null;
		String prefix = sequenceService.getFamilyNextSequence();
		String suffix = DateUtil.millisCurrentTime();
		fcode = prefix + suffix;
		return fcode;
	}

	@Override
	public ApprovalRequest createApprovalRequest(ApprovalRequest req) {
		ApprovalRequest r = null;
		if(req != null) {		
			req.setRequestStatus(JoinFamilyStatus.PENDING.getText());
			r = repositoryService.saveApprovalRequest(req);
			if (r != null) {
				PersonalInfo info = repositoryService.getPersonalInfoByPersonalId(req.getMemberId());
				if (info == null) {
					info = new PersonalInfo();
					info.setPersonalId(req.getMemberId());
					info.setName(req.getMemberName());
					PersonalInfo u = repositoryService.savePersonalInfo(info);
					if (u != null) {
						System.out.println("approvalrequest ceated successfully and PersonalInfo added");
					} else {
						System.out.println("approvalrequest ceated successfully but failed to create PersonalInfo");
					}
				} 
			}
		}
		return r;
	}

	@Override
	public Family verifyRequestGetFamilyByFamilyCode(String familyCode, String memberId) {
		Family family = null;
		if(!StringUtils.isEmpty(familyCode) && !StringUtils.isEmpty(memberId)) {
			ApprovalRequest request = repositoryService.getApprovalRequestByApprovalId(familyCode+"_"+memberId);
			family = (request==null) ? repositoryService.getFamilyByCode(familyCode) : null;
		}		
		return family;	
	}

	@Override
	public List<ApprovalRequest> getPendingApprovalRequestByAdminId(String adminId) {
		List<ApprovalRequest> requestList = null;
		if(!StringUtils.isEmpty(adminId)) {
			requestList = repositoryService.getAllPendingApprovalRequestForAdmin(adminId);
		}		
		return requestList;
	}

	@Override
	public ApprovalRequest approveRejectFamilyJoinRequest(ApprovalRequest request) {
		ApprovalRequest req = null;
		List<SubscribedFamily> list = null;
		if(request!=null) {
			if(request.getRequestStatus().equals(JoinFamilyStatus.REJECTED.getText())){
				req = repositoryService.saveApprovalRequest(request);
			}
			if(request.getRequestStatus().equals(JoinFamilyStatus.APPROVED.getText())) {
				req = repositoryService.saveApprovalRequest(request);
				if(req != null && !StringUtils.isEmpty(req.getRequestStatus()) && 
						req.getRequestStatus().equals(JoinFamilyStatus.APPROVED.getText()) &&
						!StringUtils.isEmpty(req.getMemberId())) {
					System.out.println("Request approved successfully");
					PersonalInfo info = repositoryService.getPersonalInfoByPersonalId(request.getMemberId());
					SubscribedFamily sf = new SubscribedFamily();
					sf.setFamilyId(request.getFamilyId());
					sf.setFamilyName(request.getFamilyName());
					sf.setFamilySide(request.getFamilySide());
					sf.setFamilyType(request.getFamilyType());					
					list = info.getSubscribedFamilies();
					if(!CollectionUtils.isEmpty(list)) { 
						list.add(sf);
					} else {
						list = new ArrayList<SubscribedFamily>();
						list.add(sf);
					}
					info.setSubscribedFamilies(list);
					PersonalInfo p = repositoryService.savePersonalInfo(info);
					if(p != null) {
						System.out.println("PersonalInfo updated with subscribed families");
					} else {
						System.out.println("Not able to update PersonalInfo for adding subscribed families");
					} 
					Family f = repositoryService.getFamilyByFamilyId(request.getFamilyId());					
					if(f != null) {
						FamilyMember fm = new FamilyMember();
						fm.setName(request.getMemberName());
						fm.setPersonalId(request.getMemberId());
						List<FamilyMember> fList = f.getFamilyMembers();
						if(!CollectionUtils.isEmpty(fList)) {
							fList.add(fm);
						} else {
							fList = new ArrayList<FamilyMember>(); 
							fList.add(fm);
						}
						f.setFamilyMembers(fList);
						Family fam = repositoryService.saveFamilyAccount(f);
						if(fam != null) {
							System.out.println("familyList updated for user after admin approval");
						} else {
							System.out.println("Not able to update familyList for user after admin approval");
						}						
					} else {
						System.out.println("after admin approval, not able to retrieve family");
					} 
				}
			}			
		}		
		return req;
	}

	

}
