package com.app.family.repository;

import java.util.List;

import com.app.family.models.LoginInfo;
import com.app.family.models.VerificationInfo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends MongoRepository<VerificationInfo, String> {
	 List<VerificationInfo> findByToken(String token);
	 List<VerificationInfo> findByUser(LoginInfo user);
}
