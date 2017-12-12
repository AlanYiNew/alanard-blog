package com.alanard.pojo;

import java.io.Serializable;
import java.util.Set;



public class User implements Serializable{
	/**
	 * 
	 */


	private Integer id;
	private String username;
	private String password;
	private String email;
	private String pofolioPic;
	private String introduction;
	private Integer userLevel;
	private String background;
	private String github;
	

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public User(){
		
	}
	
	public User(String username, String password, String name, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {	
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return pofolioPic;
	}

	public void setAvatar(String pofolioPic) {
		this.pofolioPic = pofolioPic;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getKeyGen() {
		return User.class.getSimpleName()+"="+id;
	}
	
	private static final long serialVersionUID = 3702662868551977441L;
	public String getPofolioPic() {
		return pofolioPic;
	}

	public void setPofolioPic(String pofolioPic) {
		this.pofolioPic = pofolioPic;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}
}
