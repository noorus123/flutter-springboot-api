package com.email.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.email.model.User;
import com.email.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
	 List<VerificationToken> findByToken(String token);
	 List<VerificationToken> findByUser(User user);
}
