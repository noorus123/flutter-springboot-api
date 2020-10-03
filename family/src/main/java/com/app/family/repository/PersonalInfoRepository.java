package com.app.family.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.family.models.PersonalInfo;


@Repository
public interface PersonalInfoRepository extends MongoRepository<PersonalInfo, String> {
	

	public PersonalInfo findByPersonalId(String personalId);
	
	//except _id all other fields are excluded by default if not included using 1
	@Query(value="{}", fields="{_id: 0, personalId:1, name:1, gender:1}") 
	public List<PersonalInfo> getAllPersonalInfoNamePIdGender();
}
