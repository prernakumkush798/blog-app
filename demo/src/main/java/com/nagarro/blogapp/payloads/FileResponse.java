package com.nagarro.blogapp.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {
	String fileName;
	String message;

}
