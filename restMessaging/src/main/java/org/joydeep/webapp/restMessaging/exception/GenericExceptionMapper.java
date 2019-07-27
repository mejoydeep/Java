package org.joydeep.webapp.restMessaging.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		org.joydeep.webapp.restMessaging.resources.ErrorMessage errorMessage=new org.joydeep.webapp.restMessaging.resources.ErrorMessage(exception.getMessage(),504,"www.google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}

