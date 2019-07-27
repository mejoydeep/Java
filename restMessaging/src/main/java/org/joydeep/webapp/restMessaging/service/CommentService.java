package org.joydeep.webapp.restMessaging.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.Errors.ErrorMessage;
import org.joydeep.webapp.restMessaging.database.DatabaseClass;
import org.joydeep.webapp.restMessaging.model.Comment;
import org.joydeep.webapp.restMessaging.model.Message;

public class CommentService {

	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
		
	}
	
	//Getting comment and handle Exception "WebApplicationException"
	public Comment getComment(long messageId,long commentId) {
		
		  org.joydeep.webapp.restMessaging.resources.ErrorMessage errorMessage=new
		  org.joydeep.webapp.restMessaging.resources.ErrorMessage("Not Found",404,
		 "http://www.google.com"); Response response=
		 Response.status(Status.NOT_FOUND).entity(errorMessage).build();
		 
		
		Message message=messages.get(messageId);
		if(message ==null) {
			throw new WebApplicationException(response); //It handles exception and this class is a parent class, This is not the best way to write code inside the service method.Insted of use separate exception Handler class
		}
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		Comment comment=comments.get(commentId);
		if(comment==null) {
			throw new NotFoundException(response); //It is a subclass of 'WebApplicationException'
		}
		return comment;
	}
	
	public Comment addComment(long messageId,Comment comment) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return comments.remove(commentId);
		
	}
}
