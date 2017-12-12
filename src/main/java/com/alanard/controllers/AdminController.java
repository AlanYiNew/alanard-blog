package com.alanard.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alanard.pojo.InvitationCode;
import com.alanard.pojo.Post;
import com.alanard.pojo.User;
import com.alanard.services.impl.InvitationServiceImpl;
import com.alanard.services.impl.PostServiceImpl;
import com.alanard.services.impl.UploadServiceImpl;
import com.alanard.services.impl.UserServiceImpl;
import com.alanard.utils.PinUtil;

@EnableAutoConfiguration    
@Controller    
@RequestMapping("/admin")   
public class AdminController {
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private UploadServiceImpl uploadServiceImpl;
	@Autowired
	private PostServiceImpl postServiceImpl;
	@Autowired
	private InvitationServiceImpl invitationServiceImpl;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap map,
			HttpSession session) {
		User admin = (User) session.getAttribute("admin"); 
		if (admin != null) {
			return "redirect:/user/"+admin.getId()+"/userpage#blog";
		}	
		return "login";
	}
	
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginSubmit(User param, ModelMap map,
			HttpSession session) {

		User user = userServiceImpl.authenticateUser(param);
	
		if (user == null){
			// 6 for password invalid
			map.put("error", "6");
			return "error";
		}	else {
			session.setAttribute("admin", user);
			map.put("error", "0");
			return "error";
		}
	}
	
	@RequestMapping(value = "/signout.do", method = RequestMethod.GET)
	public String signout(User param, ModelMap map,
			HttpSession session) {
		session.removeAttribute("admin");
		map.put("error", "0");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String signup(User param, ModelMap map,
			HttpSession session, String confirmPassword,InvitationCode code) {
		
		if (!userServiceImpl.validEmail(param.getEmail())) {
			map.put("error", "9");
		}   else if  (!userServiceImpl.validPassword(param.getPassword(), confirmPassword)){
			map.put("error", "8");
		}   else if (!invitationServiceImpl.checkInvitaionCode(code)) {
			map.put("error", "7");
		}	else {
			try {
				userServiceImpl.add(param);
				map.put("error", "0");
				invitationServiceImpl.deleteByCode(code);
				session.setAttribute("admin", userServiceImpl.getUserByEmail(param));
			}	catch (DuplicateKeyException e){
				map.put("error", "3");
				
			}
		}

		return "error";
	}
	
	
	@RequestMapping(value = "/{user.id}/post/{id}/edit", method = RequestMethod.GET)
	public String edit(Post post, ModelMap map,
			HttpSession session) {
		User admin = (User)session.getAttribute("admin");
		post.setUser(admin);
		if (postServiceImpl.check(post)) {
			map.put("user", session.getAttribute("admin"));	
			map.put("post", postServiceImpl.get(post));
			return "edit";
		}	else {
			map.put("error", "1");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/{user.id}/post/{id}/edit.do", method = RequestMethod.POST)
	public String editAction(Post post, ModelMap map,
			HttpSession session) {
		User admin = (User)session.getAttribute("admin");
		post.setUser(admin);
		if (postServiceImpl.check(post)) {
			if (post.getModule() == null || post.getCategory() == null ||
				post.getModule() == "" || post.getCategory() =="") {
				map.put("error", "10");
			}	else {
				postServiceImpl.update(post);
				map.put("error", "0");
			}
			return "error";
		}	else {
			map.put("error", "1");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/{id}/changeinfo.do", method = RequestMethod.POST)
	public String changeInfoAction(User user, ModelMap map,
			HttpSession session) {
		User admin = (User)session.getAttribute("admin");
		
		if (user.getId().equals(admin.getId())) {
			admin.setBackground(user.getBackground());
			admin.setUsername(user.getUsername());
			admin.setIntroduction(user.getIntroduction());
			admin.setPofolioPic(user.getPofolioPic());
			admin.setGithub(user.getGithub());
			userServiceImpl.update(admin);
			map.put("error", "0");
			return "error";
		}	else {
			map.put("error", "13");
			return "error";
		}
		
	}
	
	@RequestMapping(value = "/{id}/changeinfo", method = RequestMethod.GET)
	public String changeInfo(User user, ModelMap map,
			HttpSession session) {
		User admin = (User)session.getAttribute("admin");
			
		if (user.getId().equals(admin.getId())) {
			map.put("user", admin);
			
			return "changeinfo";
		}	else {
			return "redirect:/"+admin.getId()+"/changeinfo";
		}
		
	}
	
	
	@RequestMapping(value = "/{id}/post/add", method = RequestMethod.GET)
	public String add(User user, ModelMap map,
			HttpSession session) {;
		
		map.put("user",session.getAttribute("admin"));
		return "add";
	}
	
	
	@RequestMapping(value = "/{post.user.id}/post/add.do", method = RequestMethod.POST)
	public String addAction(Post post, ModelMap map,
			HttpSession session,String pin) {
			User user = (User) session.getAttribute("admin");
			post.setUser(user);
			if (user.getId() == 1 && post.getTitle().equals("add code")) {
				try {
					invitationServiceImpl.addCode(post.getContext());
				}	catch (DuplicateKeyException e) {
					map.put("error", "4");
				}	catch (Exception e) {
					map.put("error", "5");
				}
			}	else if (post.getModule() == null || post.getCategory() == null ||
					     post.getModule() == "" || post.getCategory() =="") {
				map.put("error", "10");
			}	else {		
				postServiceImpl.add(post);
				map.put("error", "0");
			}
		
			return "error";
		
	}
	
	@RequestMapping(value = "/{user.id}/post/{id}/delete.do", method = RequestMethod.POST)
	public String postDelete(Post post, ModelMap map,
			HttpSession session) {
		User admin = (User)session.getAttribute("admin");
		post.setUser(admin);
		
		
		if (postServiceImpl.check(post)) {
			postServiceImpl.delete(post);
			map.put("user", admin);
			
			map.put("error", "0");
			return "error";
		}	else {
			map.put("error", "1");
			return "error";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/{uid}/upload.do", method = RequestMethod.POST)
	public Map<String, Object> upload(
			@RequestParam("file") MultipartFile file,
			HttpServletRequest request, @PathVariable("uid") int uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		String actualUrl = uploadServiceImpl.uploadFile(file,uid,request.getServletContext().getRealPath("/"), "myblog");
		map.put("success", actualUrl==null?false:true);
		map.put("file_path", actualUrl);
		return map;
	}
	
	
	@RequestMapping(value = "/sendmail.do", method = RequestMethod.POST)
	public String sendMailAction(User user,ModelMap map, HttpSession session) {
		if ((user = userServiceImpl.getUserByEmail(user)) != null) {
			Date prev = (Date) session.getAttribute("resettime");
			if (prev == null) {
				session.setAttribute("resettime",new Date());				
				userServiceImpl.sendEmail(user, session);
				map.put("error", "0");
			}	else {
				//greater than 1 minute
				long minutes = (new Date().getTime() - prev.getTime())
						/ (1000 * 60);
				if (minutes >= 1) {
					session.setAttribute("resetTime", new Date());
					userServiceImpl.sendEmail(user, session);
					map.put("error","0");				
				} else {
					map.put("error","11");
				}
			}
		}	else {
			map.put("error", "11");
		}
	
		return "error";
	}
	
	@RequestMapping(value = "/reset.do", method = RequestMethod.POST)
	public String resetAction(User user,String pin, ModelMap map, HttpSession session,String confirmPassword) {
		
		String cachePin = (String) session.getAttribute("pin");
		if (cachePin == null) {
			map.put("error", "15");
		}	else if (!cachePin.equals(pin)) {
			map.put("error", "14");
		}	else if  (!userServiceImpl.validPassword(user.getPassword(), confirmPassword)){
			map.put("error", "8");
		}	else {
			session.setAttribute("pin", null);
			userServiceImpl.resetPasswordByEmail(user);
			map.put("error", "0");
		}
	
		return "error";
	}
	
	
	@RequestMapping(value="/refreshpin")
	public void refreshpin(String stamp, HttpServletResponse response, HttpSession session) {	
		try {
			OutputStream os = response.getOutputStream();
			response.setContentType("image/jpeg");
			ImageIO.write(PinUtil.getImage(session), "JPEG", os);
			os.flush();
			os.close();
			os=null;
			response.flushBuffer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
