package com.bloggingapp.bootcampjava.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggingapp.bootcampjava.exception.ResourceNotFoundException;
import com.bloggingapp.bootcampjava.model.Post;
import com.bloggingapp.bootcampjava.model.User;
import com.bloggingapp.bootcampjava.repository.PostRepository;
import com.bloggingapp.bootcampjava.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	PostRepository postRepository;
	UserRepository userRepository;

	// Get All posts
	@GetMapping("/posts")
	public List<Post> getAllNotes() {
		return (List<Post>) postRepository.findAll();
	}

	// Create a new post
	@PostMapping("/posts")
	public Post createPost(@Valid @RequestBody Post post) {
		return postRepository.save(post);
	}
	
	
	
	

	

	// Get a Single post
	@GetMapping("/posts/{id}")
	public Post getPostById(@PathVariable(value = "id") Long userId) {
		return postRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", userId));
	}

	// Update a Post
	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable(value = "id") Long userId, @Valid @RequestBody Post noteDetails) {

		Post post = postRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		post.setTitle(post.getTitle());
		post.setBodyText(post.getBodyText());

		Post updatedUser = postRepository.save(post);
		return updatedUser;
	}

	// Delete a Post
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<?> deletePost(@PathVariable(value = "id") Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		postRepository.delete(post);

		return ResponseEntity.ok().build();
	}
}
