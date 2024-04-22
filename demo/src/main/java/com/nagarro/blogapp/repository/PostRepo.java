package com.nagarro.blogapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.blogapp.entities.Category;
import com.nagarro.blogapp.entities.Post;
import com.nagarro.blogapp.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByCategory(Category category);
	List<Post> findByUser(User user);
	List<Post> findByTitleContaining(String keyword);

}
