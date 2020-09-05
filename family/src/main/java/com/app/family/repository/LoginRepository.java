package com.app.family.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.family.models.LoginInfo;


@Repository
public interface LoginRepository extends MongoRepository<LoginInfo, String> {
	public LoginInfo findByEmail(String email);
	public LoginInfo findByPhone(String phone);	
	public LoginInfo findByFbId(String fbId);	
}
