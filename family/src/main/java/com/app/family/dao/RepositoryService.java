package com.app.family.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.family.enums.JoinFamilyStatus;
import com.app.family.models.ApprovalRequest;
import com.app.family.models.Family;
import com.app.family.models.LoginInfo;
import com.app.family.models.PersonalInfo;
import com.app.family.models.PersonalInfoDTO;
import com.app.family.models.RelationInfo;
import com.app.family.models.VerificationInfo;
import com.app.family.repository.ApprovalRequestRepository;
import com.app.family.repository.FamilyRepository;
import com.app.family.repository.LoginRepository;
import com.app.family.repository.PersonalInfoRepository;
import com.app.family.repository.RelationInfoRepository;
import com.app.family.repository.VerificationRepository;

@Service
public class RepositoryService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private VerificationRepository verificationRepository;
	
	@Autowired
	private PersonalInfoRepository personalInfoRepository;
	
	@Autowired
	private FamilyRepository familyRepository;
	
	@Autowired
	private RelationInfoRepository relationInfoRepository;

	@Autowired
	private ApprovalRequestRepository approvalRequestRepository;

	public LoginInfo saveLoginInfo(LoginInfo u) {
		System.out.println("executing ::: saveLoginInfo");
		LoginInfo user = null;
		if(u != null) {
			user = loginRepository.save(u);
		}
		return user;
	}

	public VerificationInfo saveVerificationInfo(VerificationInfo verificationInfo) {
		System.out.println("executing ::: saveVerificationInfo");
		VerificationInfo vi = null;
		if(verificationInfo != null) {
			vi = verificationRepository.save(verificationInfo);
		}else {
			System.out.println("VerificationToken :: Save opertion failed ");
		}	
		return vi;
	}
	
	public VerificationInfo getVerificationInfoByToken(String token) {
		VerificationInfo vt = null;
		if(!StringUtils.isEmpty(token)) {
			List<VerificationInfo> vList = verificationRepository.findByToken(token);
			if(!CollectionUtils.isEmpty(vList)) {
				vt = vList.get(0);
			}else {
				System.out.println("getVerificationTokenByToken :: get opertion failed ");
			}
		}		
		return vt;
	}

	public LoginInfo getUserByEmail(String email) {
		System.out.println("executing getUserByEmail :: "+email);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = loginRepository.findByEmail(email);
			System.out.println("email retrieved user :: "+usr);
		}		
		return usr;
	}

	public VerificationInfo getVerificationInfoByUser(LoginInfo user) {
		VerificationInfo vt = null;
		if(user != null) {
			List<VerificationInfo> vList = verificationRepository.findByUser(user);
			if(!CollectionUtils.isEmpty(vList)) {
				vt = vList.get(0);
			}else {
				System.out.println("getVerificationTokenByUser :: get opertion failed ");
			}
		}		
		return vt;		
	}
	
	public void updateUserEmailVerificationStatus(VerificationInfo verificationToken, String status) {
		VerificationInfo vt = verificationRepository.findByToken(verificationToken.getToken()).get(0);
        vt.setVerificationStatus(status);
        verificationRepository.save(vt);
	}

	public LoginInfo getUserByPhone(String phone) {
		System.out.println("executing getUserByPhone :: "+phone);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(phone)) {
			usr = loginRepository.findByPhone(phone);
			System.out.println("phone retrieved user :: "+usr);
		}		
		return usr;
	}
	
	public LoginInfo getUserByFBId(String fbId) {
		System.out.println("executing getUserByFBId :: "+ fbId);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(fbId)) {
			usr = loginRepository.findByFbId(fbId);
			System.out.println("Fb retrieved user :: "+usr);
		}		
		return usr;
	}

	public LoginInfo getUserByGmail(String gmail) {
		System.out.println("executing getUserByGmail :: "+gmail);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(gmail)) {
			usr = loginRepository.findByGmail(gmail);
			System.out.println("gmail retrieved user :: "+usr);
		}		
		return usr;
	}

	public PersonalInfo savePersonalInfo(PersonalInfo u) {
		System.out.println("executing ::: savePersonalInfo");
		PersonalInfo user = null;
		if(u != null) {
			user = personalInfoRepository.save(u);
		}
		return user;
	}

	public PersonalInfo getPersonalInfoByPersonalId(String loginId) {
		System.out.println("executing ::: getPersonalInfoByPersonalId "+ loginId);
		PersonalInfo usr = null;
		if(!StringUtils.isEmpty(loginId)) {
			usr = personalInfoRepository.findByPersonalId(loginId);
			System.out.println("retrieved PersonalInfo of user :: "+usr);
		}		
		return usr;
	}

	public Family saveFamilyAccount(Family family) {		
		System.out.println("executing ::: saveFamilyAccount");
		Family f = null;
		if(family != null) {
			f = familyRepository.save(family);
		}
		return f;		
	}

	public ApprovalRequest saveApprovalRequest(ApprovalRequest request) {
		System.out.println("executing ::: saveApprovalRequest");
		ApprovalRequest f = null;
		if(request != null) {
			f = approvalRequestRepository.save(request);
		}
		return f;
		
	}

	public Family getFamilyByCode(String text) {
		System.out.println("executing getFamilyByCode :: "+text);
		Family family = null;
		if(!StringUtils.isEmpty(text)) {
			family = familyRepository.findByFamilyCode(text);
			System.out.println("retrieved family :: "+family);
		}		
		return family;
	}
	public Family getFamilyByFamilyId(String familyId) {
		System.out.println("executing getFamilyByFamilyId :: "+familyId);
		Family family = null;
		if(!StringUtils.isEmpty(familyId)) {
			family = familyRepository.findByFamilyId(familyId);
			System.out.println("retrieved family :: "+family);
		}		
		return family;
	}

	public List<ApprovalRequest> getAllPendingApprovalRequestForAdmin(String adminId) {
		System.out.println("executing getAllApprovalRequestForAdmin :: "+adminId);
		List<ApprovalRequest> requestList = null;
		if(!StringUtils.isEmpty(adminId)) {
			requestList = approvalRequestRepository.findByAdminIdAndRequestStatus(adminId, JoinFamilyStatus.PENDING.getText());
			System.out.println("retrieved AllPendingApprovalRequestForAdmin :: "+ requestList);
		}		
		return requestList;
	}

	public ApprovalRequest deleteApprovalRequest(ApprovalRequest request) {
		System.out.println("executing ::: deleteApprovalRequest");
		ApprovalRequest appReq = (request != null) ? getApprovalRequestByApprovalId(request.getApprovalId()) : null;
		if(appReq != null) {
			try {
			approvalRequestRepository.delete(appReq);
			System.out.println("rejected approval request successfully ");
			}catch(Exception e) {
				System.out.println("Not able to reject approval request"+e.getMessage());
			}
		}
		return appReq;
	}

	public ApprovalRequest getApprovalRequestByApprovalId(String approvalId) {
		System.out.println("executing getApprovalRequestByApprovalId :: "+approvalId);
		ApprovalRequest request = null;
		if(!StringUtils.isEmpty(approvalId)) {
			request = approvalRequestRepository.findByApprovalId(approvalId);
			System.out.println("retrieved approvalId :: "+request);
		}		
		return request;
	}


	public List<PersonalInfoDTO> getAllPersonalInfoSelectedFields() {
		System.out.println("executing getAllPersonalInfoSelectedFields :: ");
		List<PersonalInfo> pList = null; 
		List<PersonalInfoDTO> dtoList = new ArrayList<PersonalInfoDTO>(); 
		pList =	personalInfoRepository.getAllPersonalInfoNamePIdGender();
		if(!CollectionUtils.isEmpty(pList)) {
				pList.forEach((e)->{
					PersonalInfoDTO dto = new PersonalInfoDTO();
					BeanUtils.copyProperties(e, dto);
					dtoList.add(dto);
				});
		}		
		System.out.println("retrieved allPersonalInfoDTO :: "+ pList);
		return dtoList != null ? dtoList : null; 
	}

	
	public RelationInfo saveRelationInfo(RelationInfo relInfo) {
		System.out.println("executing ::: saveRelationInfo");
		RelationInfo rel = null;
		if(relInfo != null) {
			rel = relationInfoRepository.save(relInfo);
		}
		return rel;
	}


	
}
