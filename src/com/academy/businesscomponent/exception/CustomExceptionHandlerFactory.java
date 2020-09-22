package com.academy.businesscomponent.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
// serviva per per le jsf
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory{
	private ExceptionHandlerFactory exceptionHandlerFactory;
	
	public CustomExceptionHandlerFactory(ExceptionHandlerFactory wrapped) {
		super(wrapped);
		this.exceptionHandlerFactory = wrapped;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		return new CustomErrorHandler(exceptionHandlerFactory.getExceptionHandler());
	}
}
