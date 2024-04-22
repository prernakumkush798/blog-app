package com.nagarro.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.blogapp.entities.User;

@Repository
public interface userRepository extends JpaRepository<User, Integer>{

}
