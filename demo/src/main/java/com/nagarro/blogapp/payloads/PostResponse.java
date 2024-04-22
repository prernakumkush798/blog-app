package com.nagarro.blogapp.payloads;

import java.util.List;

import lombok.NoArgsConstructor;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalElement;
	private int totalPages;
	private boolean isLast;

}
