package com.greatlearning.employeemgmtsystem.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.greatlearning.employeemgmtsystem.model.Role;
import com.greatlearning.employeemgmtsystem.model.User;
import com.greatlearning.employeemgmtsystem.repository.RoleRepository;
import com.greatlearning.employeemgmtsystem.repository.UserRepository;

@Component
public class DbInit {
	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	/**
	 * Using the post construct we are adding the default Role and User with default
	 * Role and encrypted password to the repository when the application starts if
	 * and only if the repository has no default role and user.
	 */

	@PostConstruct
	private void postConstruct() {
		try {
			Role role;
			User user;
			if (roleRepo.findOneByName("db_admin") != null) {
				role = roleRepo.findOneByName("db_admin");
			} else {
				role = new Role("db_admin");
				roleRepo.save(role);
			}

			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);

			if (userRepo.findFirstByUsername("db_admin") != null) {
				user = userRepo.findFirstByUsername("db_admin");
			} else {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user = new User("db_admin", passwordEncoder.encode("db_admin"), roleList);
				userRepo.save(user);
			}
		} catch (Exception e) {
			System.out.println("User/Role exists.");
		}
	}
}
