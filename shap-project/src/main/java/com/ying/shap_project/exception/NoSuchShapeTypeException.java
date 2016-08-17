package com.ying.shap_project.exception;

public class NoSuchShapeTypeException extends RuntimeException {
	
	public NoSuchShapeTypeException () {
		super();
	}
	
	public NoSuchShapeTypeException (String message) {
		super(message);
	}

}
