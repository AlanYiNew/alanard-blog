package com.alanard.services.impl;

import javax.servlet.http.HttpSession;


import com.alanard.pojo.User;

public class AuthenticationServiceImpl {


	public boolean loginValidation(HttpSession session,String uid) {
		return session.getAttribute("user") !=null && session.getAttribute("user").equals(uid);
	}

	public int getUserId(HttpSession session) {
		return (Integer) session.getAttribute("user");
	}

	public boolean authentication(HttpSession session, int uid) {
		return uid == ((User)session.getAttribute("user")).getId();
	}

	public boolean loginOrNot(HttpSession session) {
		return session.getAttribute("user")==null?false:true;
	}

	public boolean pinValidation(String validPin, String pin) {	
		return pin != null && pin.toUpperCase().equals(validPin);
	}

}
