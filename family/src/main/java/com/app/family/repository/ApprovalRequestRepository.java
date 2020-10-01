package com.app.family.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.family.models.ApprovalRequest;


@Repository
public interface ApprovalRequestRepository extends MongoRepository<ApprovalRequest, String> {

	public List<ApprovalRequest> findByAdminId(String adminId);

	public ApprovalRequest findByApprovalId(String approvalId);

	public List<ApprovalRequest> findByAdminIdAndRequestStatus(String adminId, String status);
	

	
}
