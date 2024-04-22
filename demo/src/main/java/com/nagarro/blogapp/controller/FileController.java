package com.nagarro.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.blogapp.payloads.FileResponse;
import com.nagarro.blogapp.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;

	@Value("${project.image}")
	String path;

	@PostMapping("/upload")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {
		String file = this.fileService.uploadImage(path, image);
		return new ResponseEntity<FileResponse>(new FileResponse(file, "Image uploaded successfully!!"), HttpStatus.OK);
	}
}
