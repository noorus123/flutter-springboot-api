package com.app.family.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.family.dao.RepositoryService;
import com.app.family.models.Admin;
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
					System.out.println(
							"Family Account linked with Admin, PersonalInfo updated successfully " + u.toString());
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

}
