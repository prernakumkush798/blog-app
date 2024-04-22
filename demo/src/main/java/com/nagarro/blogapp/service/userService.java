package com.nagarro.blogapp.service;

import java.util.List;

import com.nagarro.blogapp.payloads.UserDto;

public interface userService {
	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto userdto,int id);

	UserDto getUserById(int id);

	List<UserDto> listOfUser();

	void deleteUser(int id);
	

}
