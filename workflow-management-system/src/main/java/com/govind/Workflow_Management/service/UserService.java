package com.govind.Workflow_Management.service;

import org.springframework.stereotype.Service;

import com.govind.Workflow_Management.Exception.ResourceNotFound;
import com.govind.Workflow_Management.dto.UserRequest;
import com.govind.Workflow_Management.entity.User;
import com.govind.Workflow_Management.repository.UserRepository;

import java.util.List;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepo;

	public User addUser(UserRequest dto) {
		User user = new User();
		user.setUsername(dto.username());
		user.setEmail(dto.email());
		user.setPassword(dto.password());
		user.setFullName(dto.fullName());
		return userRepo.save(user);
	}

	public List<User> readAll() {
		return userRepo.findAll();
	}

	public User findById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User not found"));
	}

	public void deleteUser(Long id) {
		User user = findById(id);
		userRepo.delete(user);
	}
}
