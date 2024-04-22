package com.nagarro.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.blogapp.entities.Post;
import com.nagarro.blogapp.payloads.ApiResponse;
import com.nagarro.blogapp.payloads.PostDto;
import com.nagarro.blogapp.payloads.PostResponse;
import com.nagarro.blogapp.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("user/{userid}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userid,
			@PathVariable int categoryId) {
		PostDto post = this.postService.addPost(postDto, userid, categoryId);
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable int userId) {
		List<PostDto> posts = this.postService.getPostByUserid(userId);
		return new ResponseEntity<>(posts, HttpStatus.FOUND);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable int categoryId) {
		List<PostDto> post = this.postService.getPostbyCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(post, HttpStatus.FOUND);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = "postId",required=false) String sortBy) {
		PostResponse posts = this.postService.getAllPost(pageNumber, pageSize,sortBy);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.FOUND);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> getPostByKeyword(@PathVariable String keyword){
		List<PostDto> posts=this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.FOUND); 
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
		PostDto postDto = this.postService.getPost(id);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.FOUND);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable int id) {
		this.postService.deletePost(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable int id) {
		PostDto updatedpost = this.postService.updatePost(postDto, id);
		return new ResponseEntity<PostDto>(updatedpost, HttpStatus.OK);

	}
}
