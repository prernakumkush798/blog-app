package com.nagarro.blogapp.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import com.nagarro.blogapp.payloads.CategoryDto;
import com.nagarro.blogapp.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService service;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categorydto) {
		CategoryDto category = this.service.addCategory(categorydto);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);

	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categorydto,
			@PathVariable("id") int id) {
		CategoryDto updatedCategory = this.service.updateCategory(categorydto, id);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int id) {
		CategoryDto category = this.service.getCategoryDto(id);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int id) {
		this.service.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted", true), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> categories = this.service.getCategory();
	return ResponseEntity.ok(categories);
	}

}
