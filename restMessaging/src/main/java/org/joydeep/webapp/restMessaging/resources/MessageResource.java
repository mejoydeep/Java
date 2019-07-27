package org.joydeep.webapp.restMessaging.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.joydeep.webapp.restMessaging.model.Link;
import org.joydeep.webapp.restMessaging.model.Message;
import org.joydeep.webapp.restMessaging.resources.beans.MessengerBeanParam;
import org.joydeep.webapp.restMessaging.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService=new MessageService();

	//Get all the messages 
	@GET
	@Produces(value= {MediaType.APPLICATION_JSON, MediaType.TEXT_XML} ) //Different type of format can be produced based on the requirement
	public List<Message> getMessages(@BeanParam MessengerBeanParam beanParam) {
	    if(beanParam.getYear()>0){
	    	return messageService.getAllMessageForYear(beanParam.getYear());
	    }
	    if (beanParam.getStart()>0 && beanParam.getSize()>0) {
			return messageService.getAllMessagePaginated(beanParam.getStart(), beanParam.getStart()+beanParam.getSize());
		}
		return messageService.getAllMessages();
	}
	//Create a new message by its id
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage=messageService.addMessage(message);
		//return Response.status(Status.CREATED).entity(newMessage).build(); // Status code response
		
		String newId=String.valueOf(newMessage.getId()); 
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();//// Location Header
		return Response.created(uri).entity(newMessage).build();
		//return messageService.addMessage(message);
	}
	//Update a particular message by its id
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	//Delete a particular message by its id
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId")long id) {
		 messageService.removeMessage(id);
	}
	
	//Get a particular message by its id resource mapping with URL
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {	
		 Message message=messageService.getMessage(id);
		 message.addLink(getURIForSelf(uriInfo, message), "self");
		 message.addLink(getURIForProfile(uriInfo,message), "profile");
		 message.addLink(getURIForComment(uriInfo,message), "comment");
		 
		 return message; 
	}
	
	
	//Sub-resource concept
	
	@Path("/{messageId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
	
	
	//creates the URI and adds this to the link
	public String getURIForSelf(UriInfo uriInfo, Message message) {
		 String uri=uriInfo.getBaseUriBuilder()
				 .path(MessageResource.class)
				 .path(Long.toString(message.getId()))
				 .build()
				 .toString();
		 return uri;
	}
	
	//create URI for Profile and send this as a response
	public String getURIForProfile(UriInfo uriInfo,Message message) {
		URI uri=uriInfo.getBaseUriBuilder()
				.path(ProfileResource.class)
				.path(message.getAuthor())
				.build();
		return	uri.toString();
				
	}
	
	//Adding comment with the response and send it back
	public String getURIForComment(UriInfo uriInfo, Message message) {
		URI uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId()) //mapping messageId field
				.build();
				return uri.toString();
	}
	
}
