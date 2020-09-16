package com.app.family.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.family.models.PersonalInfo;


@Repository
public interface PersonalInfoRepository extends MongoRepository<PersonalInfo, String> {
		
	
}
