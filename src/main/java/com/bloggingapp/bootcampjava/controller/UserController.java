package com.bloggingapp.bootcampjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.bloggingapp.bootcampjava.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bloggingapp.bootcampjava.exception.ResourceNotFoundException;
import com.bloggingapp.bootcampjava.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepository;

	// Get All user
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// Create a new User
	@PostMapping("/users")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	// Get a Single user
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	// Update a User
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User noteDetails) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(user.getName());
		user.setEmail(user.getEmail());

		User updatedUser = userRepository.save(user);
		return updatedUser;
	}

	// Delete a user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.delete(user);

		return ResponseEntity.ok().build();
	}
}
