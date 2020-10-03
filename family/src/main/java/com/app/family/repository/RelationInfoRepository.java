package com.app.family.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.family.models.RelationInfo;


@Repository
public interface RelationInfoRepository extends MongoRepository<RelationInfo, String> {


		
	
}
