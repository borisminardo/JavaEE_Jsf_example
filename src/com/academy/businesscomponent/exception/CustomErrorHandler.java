package com.academy.businesscomponent.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
// serviva per per le jsf
public class CustomErrorHandler extends ExceptionHandlerWrapper {
	private ExceptionHandler exceptionHandler;
	
	public CustomErrorHandler(ExceptionHandler wrapped) {
		super(wrapped);
		this.exceptionHandler = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return exceptionHandler;
	}

	@Override
	public void handle() throws FacesException {
		final Iterator<ExceptionQueuedEvent> coda =
			getHandledExceptionQueuedEvents().iterator();
		
		while(coda.hasNext()) {
			ExceptionQueuedEvent elemento = coda.next();
			ExceptionQueuedEventContext exceptionQueuedEventContext =
					(ExceptionQueuedEventContext) elemento.getSource();
			
			try {
				Throwable eccezione = exceptionQueuedEventContext.getException();
				System.err.println("Exception: "+ eccezione.getMessage());
				
				FacesContext contesto = FacesContext.getCurrentInstance();
				Map<String,Object> requestMap = contesto.getExternalContext().getRequestMap();
				NavigationHandler nav = contesto.getApplication().getNavigationHandler();
				
				requestMap.put("error-message",eccezione.getMessage());
				requestMap.put("error-stack",eccezione.getStackTrace());
				
				nav.handleNavigation(contesto, null, "/error");
				contesto.renderResponse();
			} finally {
				coda.remove();
				
			}
		}
	}
}
