package com.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.mongodb.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}