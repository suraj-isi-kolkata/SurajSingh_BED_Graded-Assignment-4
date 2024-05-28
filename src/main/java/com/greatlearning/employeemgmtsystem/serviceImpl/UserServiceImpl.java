package com.greatlearning.employeemgmtsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemgmtsystem.model.Role;
import com.greatlearning.employeemgmtsystem.model.User;
import com.greatlearning.employeemgmtsystem.repository.RoleRepository;
import com.greatlearning.employeemgmtsystem.repository.UserRepository;
import com.greatlearning.employeemgmtsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	/**
	 * addNewUser() will check if the input user is available. If user not
	 * available, check if the role names and their corresponding id of the input
	 * user are correct. If not, update with the correct id. If the role name itself
	 * is unavailable, set the role to the default - "db_admin" & its id. Update the
	 * password by encoding and then save this user. Return a message with
	 * 201-Created HTTP status. If the input user is available, return a
	 * 409-Conflict HTTP status with a message.
	 */

	@Override
	public ResponseEntity<String> addNewUser(User user) {
		User existingUser = userRepo.getUserByUsername(user.getUsername());
		if (existingUser == null) {
			List<Role> roleList = user.getRoles();
			for (Role role : roleList) {
				Role newRole = (roleRepo.findOneByName(role.getName()) != null) ? roleRepo.findOneByName(role.getName())
						: roleRepo.findOneByName("db_admin");
				role.setId(newRole.getId());
			}
			user.setRoles(roleList);
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepo.save(user);
			return new ResponseEntity<String>("New user added.", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("This username already exists.", HttpStatus.CONFLICT);
		}
	}

}
