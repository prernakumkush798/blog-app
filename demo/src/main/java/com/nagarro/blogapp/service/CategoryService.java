package com.nagarro.blogapp.service;

import java.util.List;

import com.nagarro.blogapp.entities.Category;
import com.nagarro.blogapp.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto addCategory(CategoryDto categorydto);
	public CategoryDto updateCategory(CategoryDto categorydto,int categoryId);
	public List<CategoryDto> getCategory();
	public CategoryDto getCategoryDto(int categoryId);
	public void deleteCategory(int categoryId);
	

}
