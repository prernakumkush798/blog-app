package com.nagarro.blogapp.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(name = "postTitle", length = 10, nullable = false)
	private String title;
	@Column(name = "postContent", length = 10000)
	private String content;
	private String imageName;
	private Date dateAdded;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();

}
