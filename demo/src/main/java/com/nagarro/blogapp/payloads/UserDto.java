package com.nagarro.blogapp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min=4,message = "Name must be 4 character min")
	private String name;

	@Email
	private String email;

	@NotEmpty
	@Size(min=8,message="Password should be min 8 character")
	private String password;

	@NotEmpty(message = "Field cannot be blank")
	private String about;
}
