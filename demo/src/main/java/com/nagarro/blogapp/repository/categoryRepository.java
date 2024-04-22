package com.nagarro.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.blogapp.entities.Category;

@Repository
public interface categoryRepository extends JpaRepository<Category,Integer >{
	

}
