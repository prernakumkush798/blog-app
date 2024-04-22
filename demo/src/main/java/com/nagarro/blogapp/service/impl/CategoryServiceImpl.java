package com.nagarro.blogapp.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.blogapp.entities.Category;
import com.nagarro.blogapp.exceptions.ResourceNotFound;
import com.nagarro.blogapp.payloads.CategoryDto;
import com.nagarro.blogapp.repository.categoryRepository;
import com.nagarro.blogapp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private categoryRepository repo;

	@Override
	public CategoryDto addCategory(CategoryDto categorydto) {
		Category savedCategory = this.repo.save(categorydtoTocategory(categorydto));
		return categorytocategorydto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, int categoryId) {
		Category category=this.repo.findById(categoryId).orElseThrow(()->new ResourceNotFound("Category", "categoryId", categoryId));
		category.setCategoryDescription(categorydto.getCategoryDescription());
		category.setCategoryTitle(categorydto.getCategoryTitle());
	    Category updateCategory = this.repo.save(category); // Fixed this line

		return categorytocategorydto(updateCategory);
	}

	@Override
	public List<CategoryDto> getCategory() {
		List<Category> categories=this.repo.findAll();
		List<CategoryDto> categoriesDto=categories.stream().map((category)->(
			categorytocategorydto(category))).collect(Collectors.toList());
		return categoriesDto;
	}

	@Override
	public CategoryDto getCategoryDto(int categoryId) {
		Category category= this.repo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category", "Category id", categoryId));
		return categorytocategorydto(category);
	}

	@Override
	public void deleteCategory(int categoryId) {
		 this.repo.deleteById(categoryId);

	}

	public Category categorydtoTocategory(CategoryDto categorydto) {
		Category category = this.modelmapper.map(categorydto, Category.class);
		return category;
	}

	public CategoryDto categorytocategorydto(Category category) {
		CategoryDto categoryDto = this.modelmapper.map(category, CategoryDto.class);
		return categoryDto;
	}
}
