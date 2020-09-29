package com.app.family.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.family.models.ApprovalRequest;


@Repository
public interface ApprovalRequestRepository extends MongoRepository<ApprovalRequest, String> {

	List<ApprovalRequest> findByAdminId(String adminId);

	ApprovalRequest findByApprovalId(String approvalId);
	

	
}
