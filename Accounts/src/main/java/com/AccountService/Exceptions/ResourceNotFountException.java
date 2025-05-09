package com.AccountService.Exceptions;

public class ResourceNotFountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ResourceNotFountException(String resourceName ,String fieldName,String fieldValue) {
		super(String.format("%s with the %s doesnot exist : %s", resourceName,fieldName,fieldValue));
	}
	

}
