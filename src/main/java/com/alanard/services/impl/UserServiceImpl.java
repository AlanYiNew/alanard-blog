package com.alanard.services.impl;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alanard.dao.UserMapper;
import com.alanard.pojo.User;
import com.alanard.services.BaseService;
import com.alanard.utils.Page;

@Service
public class UserServiceImpl implements BaseService<User> {
	@Autowired
	private UserMapper usermapper;
	@Autowired
	JavaMailSender jms;

	@Override
	public int add(User obj) {
		return usermapper.add(obj);
	}

	@Override
	@CachePut(value = "user", key = "#obj.keyGen")
	public User update(User obj) {
		usermapper.update(obj);
		return obj;
	}

	@Override
	@CacheEvict(value = "user", key = "#obj.keyGen")
	public int delete(User obj) {
		return usermapper.delete(obj);
		
	}

	@Override
	@Cacheable(value = "user", key = "#obj.keyGen")
	public User get(User obj) {
		return usermapper.get(obj);
	}

	public User authenticateUser(User user) {
		return usermapper.authenticateUser(user);
	}
	

	public List<User> getUserListByReadings(Page page){
		return usermapper.getUserListByReadings(page);
	}

	/*public boolean validEmail(User user) {
		return FormatUtil.validEmail(user.getEmail()) && usermapper.existsEmail(user);
	}*/

	public void sendEmail(User user, HttpSession session) {

		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("alanard@aliyun.com");
		mail.setTo(user.getEmail());
		String name = user.getUsername();
		
		mail.setSubject("Password resetting service");
	
		String pin = UUID.randomUUID().toString().substring(0, 8);
		session.setAttribute("pin", pin);
		String text = "Hello "+name+".\nYour pin for your password resetting service is "
				+ pin
				+ ". If you didn't apply for this service, please ignore this email. \nThank you.";
		mail.setText(text);
		jms.send(mail);
	}

	public boolean validPassword(String password1, String password2) {
		return !(password1 == null || password2 == null
				|| !password1.equals(password2) || password1.length() <= 7);
	}
	
	@CacheEvict(value = "user",allEntries=true)
	public void cleanup() {}

	public boolean validEmail(String email) {
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public User getUserByEmail(User user) {
		return usermapper.getUserByEmail(user);
	}

	public void resetPasswordByEmail(User user) {
		usermapper.resetPasswordByEmail(user);
	}

	
}
