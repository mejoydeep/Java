package org.joydeep.webapp.restMessaging.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider //It registers the exception
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		org.joydeep.webapp.restMessaging.resources.ErrorMessage errorMessage=new org.joydeep.webapp.restMessaging.resources.ErrorMessage(exception.getMessage(),404,"www.google.com");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}

}
