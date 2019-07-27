package org.joydeep.webapp.restMessaging.resources;



import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET()
	@Path("/annotation")
	public String getParaUsingAnnotation(@MatrixParam("matrix") String matrixParam,
			                             @HeaderParam("customHeader") String header,
			                             @CookieParam("cookie") String cookieName) {
		return "MatrixParam: " +matrixParam +" Header param: "+header+ " Cookie:"+cookieName;
	}
	
	@GET
	@Path("context")
	public String getStringUsingContext(@Context UriInfo uriInfo,
			                           @Context HttpHeaders headers) {
		String path=uriInfo.getAbsolutePath().toString();
		String cookies=headers.getCookies().toString();
		return "Path:"+path+ " Cookies:"+cookies;
	}

}
