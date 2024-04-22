
package com.nagarro.blogapp.payloads;

import java.util.Date;

import com.nagarro.blogapp.entities.Category;
import com.nagarro.blogapp.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date dateAdded;
	
	private CategoryDto category;
	
	private UserDto user;
	

}
