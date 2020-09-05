package com.app.family.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.app.family.models.LoginInfo;
import com.app.family.models.VerificationInfo;
import com.app.family.repository.LoginRepository;
import com.app.family.repository.VerificationRepository;

@Service
public class RepositoryService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private VerificationRepository verificationRepository;

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
		System.out.println("dao email :: "+email);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = loginRepository.findByEmail(email);
			System.out.println("dao email retrieved user :: "+usr);
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
		System.out.println("dao phone :: "+phone);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(phone)) {
			usr = loginRepository.findByPhone(phone);
			System.out.println("dao phone retrieved user :: "+usr);
		}		
		return usr;
	}
	
	public LoginInfo getUserByFBId(String fbId) {
		System.out.println("dao phone :: "+ fbId);
		LoginInfo usr = null;
		if(!StringUtils.isEmpty(fbId)) {
			usr = loginRepository.findByFbId(fbId);
			System.out.println("dao Fb retrieved user :: "+usr);
		}		
		return usr;
	}

}