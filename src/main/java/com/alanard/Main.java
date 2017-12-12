package com.alanard;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alanard.services.impl.InvitationServiceImpl;
import com.alanard.services.impl.PostServiceImpl;
import com.alanard.services.impl.UserServiceImpl;



@Controller
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@ServletComponentScan
@EnableCaching
@MapperScan("com.alanard.dao")
public class Main {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	PostServiceImpl postServiceImpl;
	@Autowired
	InvitationServiceImpl invitationServiceImpl;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Main.class,args);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap map,
			HttpSession session) {
		map.put("index", true);
		return "forward:/user/1/userpage";
	}
	
	@PreDestroy
	public void cleanup() {
		userServiceImpl.cleanup();
		postServiceImpl.cleanup();
		invitationServiceImpl.cleanup();
	}
	
}
