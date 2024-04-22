package com.nagarro.blogapp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.blogapp.service.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) {
		String nameOfFile=file.getOriginalFilename();
		
		
		String randomId=UUID.randomUUID().toString();
		String fileName=randomId.concat(nameOfFile.substring(nameOfFile.lastIndexOf(".")));
		String newPath=path+File.separator+nameOfFile;

		File newFile=new File(path);
		if(!newFile.exists()) {
			newFile.mkdir();
			
		}
		try {
			Files.copy(file.getInputStream(), Paths.get(newPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nameOfFile;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullpath="";
		try {
			fullpath = path+File.separator+fileName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream inputStream=new FileInputStream(fullpath);
		return inputStream;
	}

}
