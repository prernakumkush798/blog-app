package com.nagarro.blogapp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nagarro.blogapp.entities.Category;
import com.nagarro.blogapp.entities.Post;
import com.nagarro.blogapp.entities.User;
import com.nagarro.blogapp.exceptions.ResourceNotFound;
import com.nagarro.blogapp.payloads.PostDto;
import com.nagarro.blogapp.payloads.PostResponse;
import com.nagarro.blogapp.repository.PostRepo;
import com.nagarro.blogapp.repository.categoryRepository;
import com.nagarro.blogapp.repository.userRepository;
import com.nagarro.blogapp.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	public PostRepo postRepo;

	@Autowired
	public userRepository userRepo;

	@Autowired
	public categoryRepository categoryRepo;

	@Autowired
	public ModelMapper modelmap;

	@Override
	public PostDto addPost(PostDto postdto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "UserId", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFound("Category", "CategoryId", categoryId));
		Post post = this.postdtoTopost(postdto);
		post.setImageName("default.png");
		post.setDateAdded(new Date());
		post.setUser(user);
		post.setCategory(category);
		this.postRepo.save(post);
		return this.modelmap.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, int id) {
		Post post = this.postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("Post", "id", id));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.modelmap.map(updatedPost, PostDto.class);

	}

	@Override
	public PostDto getPost(int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Post Id", postId));
		PostDto postDto = this.modelmap.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy) {
		Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> p = this.postRepo.findAll(page);
		List<Post> posts = p.getContent();
		List<PostDto> post = posts.stream().map((item) -> this.modelmap.map(item, PostDto.class))
				.collect(Collectors.toList());
		PostResponse res = new PostResponse();
		
		res.setContent(post);
		res.setLast(p.isLast());
		res.setPageNumber(p.getNumber());
		res.setPageSize(p.getSize());
		res.setTotalElement((int) p.getTotalElements());
		res.setTotalPages(p.getTotalPages());
		return res;
	}

	@Override
	public List<PostDto> getPostByUserid(int userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "User id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDto = posts.stream().map((post) -> this.modelmap.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;

	}

	@Override
	public List<PostDto> getPostbyCategory(int categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("Category", "Category id", categoryId));
		List<Post> post = this.postRepo.findByCategory(category);
		List<PostDto> posts = post.stream().map((Item) -> this.modelmap.map(Item, PostDto.class))
				.collect(Collectors.toList());
		return posts;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> post= this.postRepo.findByTitleContaining(keyword);
		List<PostDto> posts=post.stream().map((item)->this.modelmap.map(item, PostDto.class)).collect(Collectors.toList());
				return posts ;
	}

	private Post postdtoTopost(PostDto postdto) {
		Post post = new Post();
		post.setContent(postdto.getContent());
		post.setTitle(postdto.getTitle());
		return post;
	}

	private PostDto postToPostDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getTitle());
		return postDto;
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("Post", "Post Id", postId));
		this.postRepo.delete(post);
	}

}
