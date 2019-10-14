package com.cg.frs.exception;

public class FrsException extends Exception{
		
	private static final long serialVersionUID = 3557343985224509108L;

	public FrsException() {
	}
	
	public FrsException(String message){
		super(message);
	}

}