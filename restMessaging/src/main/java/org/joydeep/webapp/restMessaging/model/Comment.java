package org.joydeep.webapp.restMessaging.model;

import java.util.Date;

public class Comment {

	private long id;
	private String mrssage;
	private Date created;
	private String author;
	
	
	public Comment() {
		
	}
	public Comment(long id, String mrssage, Date created, String author) {
		super();
		this.id = id;
		this.mrssage = mrssage;
		this.created = created;
		this.author = author;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMrssage() {
		return mrssage;
	}
	public void setMrssage(String mrssage) {
		this.mrssage = mrssage;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
