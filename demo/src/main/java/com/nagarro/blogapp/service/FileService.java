package com.nagarro.blogapp.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	String uploadImage(String path, MultipartFile file);
	InputStream getResource(String path,String fileName)throws FileNotFoundException;

}
