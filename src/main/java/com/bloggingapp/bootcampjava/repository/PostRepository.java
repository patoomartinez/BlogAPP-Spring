package com.bloggingapp.bootcampjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.bloggingapp.bootcampjava.model.Post;

//public interface PostRepository extends JpaRepository<Post, Long> {
public interface PostRepository extends CrudRepository<Post, Long>{

}
