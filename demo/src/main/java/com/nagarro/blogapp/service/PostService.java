package com.nagarro.blogapp.service;

import java.util.List;

import com.nagarro.blogapp.payloads.PostDto;
import com.nagarro.blogapp.payloads.PostResponse;

public interface PostService {

	PostDto addPost(PostDto post,int userId,int categoryId);

	PostDto updatePost(PostDto post,int id);
	

	PostDto getPost(int postId);

	PostResponse getAllPost(int pageNumber,int pageSize,String sortBy);

	void deletePost(int postId);

	List<PostDto> getPostbyCategory(int categoryId);
	
	List<PostDto> getPostByUserid(int userId);

	List<PostDto> searchPosts(String keyword);


}
