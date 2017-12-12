package com.alanard.pojo;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 451139589112052552L;
	/**
	 * 
	 */
	private Integer id;
	private String title;
	private String context;
	private String category;
	private java.util.Date postDate;
	private java.util.Date updateDate;
	private User user;
	private String module;
	private String postCover;

	public Post() {

	}
	
	public Post(int id){
		this.id = id;
	}

	public Post(String title, String context, Date postDate, Date updateDate,String category) {
		super();
		this.title = title;
		this.context = context;
		this.postDate = postDate;
		this.updateDate = updateDate;
		this.category = category;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public java.util.Date getPostDate() {
		return postDate;
	}

	public void setPostDate(java.util.Date postDate) {
		this.postDate = postDate;
	}

	public java.util.Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPostCover() {
		return postCover;
	}

	public void setPostCover(String postCover) {
		this.postCover = postCover;
	}
	
	public String getKeyGen() {
		String k = User.class.getSimpleName()+"="+user.getId()+Post.class.getSimpleName()+"="+id;
		return  k;
	}
	
}
