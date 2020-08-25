package com.email.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.email.model.User;
import com.email.model.VerificationToken;
import com.email.repository.UserRepository;
import com.email.repository.VerificationTokenRepository;

@Service
public class EmailDaoService {
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public void updateUserEmailVerificationStatus(VerificationToken verificationToken, String status) {
		VerificationToken vt = verificationTokenRepository.findByToken(verificationToken.getToken()).get(0);
        User u = userRepository.findById(vt.getUser().getUserId()).get();
        u.setVerificationStatus(status);
        userRepository.save(u);
	}
	
	public void saveUser(User user) {
		if(user != null) {
			userRepository.save(user);
		}else {
			System.out.println("USER :: Save opertion failed ");
		}		
	}

	public void saveVerificationToken(VerificationToken verificationToken) {
		if(verificationToken != null) {
			verificationTokenRepository.save(verificationToken);
		}else {
			System.out.println("VerificationToken :: Save opertion failed ");
		}				
	}
	
	public VerificationToken getVerificationTokenByToken(String token) {
		VerificationToken vt = null;
		if(!StringUtils.isEmpty(token)) {
			List<VerificationToken> vList = verificationTokenRepository.findByToken(token);
			if(!CollectionUtils.isEmpty(vList)) {
				vt = vList.get(0);
			}else {
				System.out.println("getVerificationTokenByToken :: get opertion failed ");
			}
		}		
		return vt;
	}

	public User getUserByEmail(String email) {
		System.out.println("dao email :: "+email);
		User usr = null;
		if(!StringUtils.isEmpty(email)) {
			usr = userRepository.findByEmail(email);
		}		
		return usr;
	}

	public VerificationToken getVerificationTokenByUser(User user) {
		VerificationToken vt = null;
		if(user != null) {
			List<VerificationToken> vList = verificationTokenRepository.findByUser(user);
			if(!CollectionUtils.isEmpty(vList)) {
				vt = vList.get(0);
			}else {
				System.out.println("getVerificationTokenByUser :: get opertion failed ");
			}
		}		
		return vt;		
	}
}
