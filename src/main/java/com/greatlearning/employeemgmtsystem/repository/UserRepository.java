package com.greatlearning.employeemgmtsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.employeemgmtsystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findFirstByUsername(String username);

	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User getUserByUsername(String username);
}
