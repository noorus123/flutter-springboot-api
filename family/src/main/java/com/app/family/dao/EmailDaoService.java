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
public class EmailDaoService {
	
	@Autowired
	VerificationRepository verificationRepository;
	
	@Autowired
	LoginRepository userRepository;
	
	public void updateUserEmailVerificationStatus(VerificationInfo verificationToken, String status) {
		VerificationInfo vt = verificationRepository.findByToken(verificationToken.getToken()).get(0);
        vt.setVerificationStatus(status);
        verificationRepository.save(vt);
	}
	
	public void saveUser(LoginInfo user) {
		if(user != null) {
			userRepository.save(user);
		}else {
			System.out.println("USER :: Save opertion failed ");
		}		
	}

	public void saveVerificationInfo(VerificationInfo verificationToken) {
		if(verificationToken != null) {
			verificationRepository.save(verificationToken);
		}else {
			System.out.println("VerificationToken :: Save opertion failed ");
		}				
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
			usr = userRepository.findByEmail(email);
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

}
