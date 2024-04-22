package com.nagarro.blogapp.exceptions;

public class ResourceNotFound extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFound(String resourceName,String fieldName,long fieldValue) {
		super(String.format("%s not found with %s=%s",resourceName,fieldName,fieldValue));
		resourceName=this.resourceName;
		fieldName=this.fieldName;
		fieldValue=this.fieldValue;
		
	}

}
