package com.govind.Workflow_Management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.govind.Workflow_Management.dto.UserRequest;
import com.govind.Workflow_Management.entity.User;
import com.govind.Workflow_Management.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody UserRequest dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(dto));
	}

	@GetMapping
	public List<User> readAll() {
		return userService.readAll();
	}

	@GetMapping("/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "User successfully deleted.";
	}
}
