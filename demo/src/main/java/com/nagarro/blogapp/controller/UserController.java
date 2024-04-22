package com.nagarro.blogapp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.blogapp.payloads.ApiResponse;
import com.nagarro.blogapp.payloads.UserDto;
import com.nagarro.blogapp.service.userService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {


	@Autowired
	private userService userService;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userdto) {
		
		UserDto user = this.userService.createUser(userdto);
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers() {
		List<UserDto> users = this.userService.listOfUser();
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto, @PathVariable int id) {
		UserDto user = this.userService.updateUser(userdto, id);
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted", true), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable int id) {
		return ResponseEntity.ok(this.userService.getUserById(id));
	}

}
