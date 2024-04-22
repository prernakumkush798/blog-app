package com.nagarro.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.blogapp.entities.User;
import com.nagarro.blogapp.exceptions.ResourceNotFound;
import com.nagarro.blogapp.payloads.UserDto;
import com.nagarro.blogapp.repository.userRepository;
import com.nagarro.blogapp.service.userService;

@Service
public class UserServiceImpl implements userService {

	@Autowired
	private userRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		User user = dtoToUser(userdto);
		this.userRepo.save(user);
		UserDto dtouser = userToDto(user);
		return dtouser;
	}

	@Override
	public UserDto updateUser(UserDto userdto, int id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "id", id));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User updatedUser = this.userRepo.save(user);
		UserDto updateUserDto = this.userToDto(updatedUser);

		return updateUserDto;
	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "Id", id));
		UserDto userdto = this.userToDto(user);
		return userdto;
	}

	@Override
	public List<UserDto> listOfUser() {
		List<User> listOfUser = this.userRepo.findAll();
		List<UserDto> listOfUserDto = listOfUser.stream().map((user) -> (this.userToDto(user)))
				.collect(Collectors.toList());

		return listOfUserDto;
	}

	@Override
	public void deleteUser(int id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("User", "Id", id));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;

	}

	private UserDto userToDto(User user) {
		UserDto userdto = this.modelMapper.map(user, UserDto.class);
		return userdto;
	}

}
