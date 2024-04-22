package com.nagarro.blogapp.payloads;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private int categoryId;
	
	@NotBlank
	@Size(min = 4, message = "Category title should have min 4 characters")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 4, message = "Category title should have min 4 characters")
	private String categoryDescription;

}
