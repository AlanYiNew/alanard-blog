package com.alanard.controllers;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.alanard.pojo.Post;
import com.alanard.pojo.User;
import com.alanard.services.impl.PostServiceImpl;
import com.alanard.services.impl.UserServiceImpl;
import com.alanard.utils.Page;

@EnableAutoConfiguration    
@Controller    
@RequestMapping("/user")   
public class PostController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	
	@RequestMapping(value = "/{id}/userpage", method = RequestMethod.GET)
	public String userpage(User user, ModelMap map,
			HttpSession session,Page page, HttpServletRequest request) {


		page.setCurrentPage(1);
		page.setPageSize(5);
		page.setUserId(user.getId());
		List<Post> posts = postServiceImpl.getPostByPage(page);
		map.put("posts", posts);
		user = userServiceImpl.get(user);
		map.put("user", user);
		return "userpage";
	}
	
	@RequestMapping(value = "/{id}/loadmore.do", method = RequestMethod.POST)
	public String loadmore(User user, ModelMap map,
			HttpSession session,Page page) {
		page.setPageSize(5);
		page.setUserId(user.getId());
		List<Post> posts = postServiceImpl.getPostByPage(page);
		map.put("posts", posts);
		return "loadmore";
	}
	
	@RequestMapping(value = "/{user.id}/post/{id}", method = RequestMethod.GET)
	public String post(Post post, ModelMap map,
			HttpSession session) {
		Post result = postServiceImpl.get(post);
		map.put("post", result);
		map.put("user", result.getUser());
		return "post";
	}
	
	@RequestMapping(value = "/{id}/tags", method = RequestMethod.GET)
	public String tags(User user, ModelMap map) {
		Map<String,List<Object>> result = postServiceImpl.getModulesAndCategories(user);
		user = userServiceImpl.get(user);
		map.put("user", user);
		map.put("result", result);	
		return "tags";
	}
}
