package com.app.family.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.family.models.Family;


@Repository
public interface FamilyRepository extends MongoRepository<Family, String> {

	
}
