package com.alanard.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.alanard.dao.PostMapper;
import com.alanard.pojo.Post;
import com.alanard.pojo.User;
import com.alanard.services.BaseService;
import com.alanard.utils.Page;


@Service
public class PostServiceImpl implements BaseService<Post> {
	@Autowired
	private PostMapper postmapper;


	public List<Post> getPostByPage(Page page) {
		List<Post> postList ;
		
		if (page.getModule() != null) {			
			postList = postmapper.getPostByModule(page);
		}	else if (page.getCategory() != null) {
			postList = postmapper.getPostByCategory(page);
		}	else {
			postList = postmapper.getPostByPage(page);
		}
		return removePic(postList);
	}

	
	private List<Post> removePic(List<Post> postList) {
		for (Post post : postList) {			
			post.setContext(post.getContext()
					.replaceAll("<img.*>.*</img>", "")
					.replaceAll("<img.*/>", "")
					.replaceAll("<img.*>", ""));
		}
		return postList;
	}
	


	// simplify to shorten the length of every post
	@SuppressWarnings("unused")
	private List<Post> simplify(List<Post> postList,int length) {
		List<Post> returnList = new ArrayList<Post>();
		for (Post post : postList) {
			Post tobeAdd = new Post();
			String newContext = post.getContext()
					.replaceAll("<img.*>.*</img>", "")
					.replaceAll("<img.*/>", "")
					.replaceAll("<img.*>", "");
			StringBuffer sb = new StringBuffer();
			
			if (newContext.length() > length) {
				// The maximum bytes
				int n = 0;
				char temp;
				boolean isCode = false;
				boolean isHTML = false;
				for (int i = 0; i < newContext.length(); i++) {
					temp = newContext.charAt(i);
					if (temp == '<') {
						isCode = true;
					} else if (temp == '&') {
						isHTML = true;
					} else if (temp == '>' && isCode) {
						n = n - 1;
						isCode = false;
					} else if (temp == ';' && isHTML) {
						isHTML = false;
					}
					if (!isCode && !isHTML) {
						n = n + 1;
					
						if ((temp + "").getBytes().length > 1) {
							n = n + 1;
						}
					}
					sb.append(temp);
					if (n >= length) {
						break;
					}
				}

				String temp_result = sb.toString().replaceAll("(>)[^<>]*(<?)",
						"$1$2");
				
				// get rid of unnecessary tags
				temp_result = temp_result
						.replaceAll(
								"</?(AREA|BASE|BASEFONT|BODY|BR|COL|COLGROUP|DD|DT|FRAME|HEAD|HR|HTML|IMG|INPUT|ISINDEX|LI|LINK|META|OPTION|PARAM|TBODY|TD|TFOOT|TH|THEAD|area|base|basefont|body|br|col|colgroup|dd|dt|frame|head|hr|html|img|input|isindex|li|link|meta|option|param|tbody|td|tfoot|th|thead|tr)[^<>]*/?>",
								"");
				// get rid of the end tags corresponding to the tags which don't
				// have to be got rid of
				temp_result = temp_result.replaceAll(
						"<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
				
				// use regular expressions to get tags
				Pattern p = Pattern.compile("<([a-zA-Z]+)[^<>]*>");
				Matcher m = p.matcher(temp_result);
				
				List<Object> endHTML = new ArrayList<Object>();
				while (m.find()) {
					endHTML.add(m.group(1));
				}
				// complete pairs without end tags
				for (int i = endHTML.size() - 1; i >= 0; i--) {
					sb.append("</");
					sb.append(endHTML.get(i));
					sb.append(">");
				}
			}
			
			tobeAdd.setContext(newContext);
			tobeAdd.setUser(post.getUser());
			tobeAdd.setCategory(post.getCategory());
			tobeAdd.setModule(post.getModule());
			tobeAdd.setUpdateDate(post.getUpdateDate());
			tobeAdd.setPostDate(post.getPostDate());
			tobeAdd.setId(post.getId());
			tobeAdd.setTitle(post.getTitle());
			tobeAdd.setPostCover(post.getPostCover());
			returnList.add(tobeAdd);
		}
		return returnList;
	}
	
	private String xssDefend(String context){
		Pattern p = Pattern.compile("</?(?!img|/?(a|pre|code|p|table|td|th|tr|span|b|i|u|tbody|br|h\\d))[^>]+>");
		Matcher m = p.matcher(context);
		String newContext = m.replaceAll("");
		int pos = 0;
		while (pos < newContext.length()){
			pos = newContext.indexOf("src=\"", pos);
			if (pos == -1) break;
			int from = pos+5;
			int to = from+7 ;
			if (to<newContext.length() && !newContext.substring(from,to).equals("http://"))
				newContext = newContext.substring(0,from)+"http://"+newContext.substring(from+1);
			pos = to;
		}
		pos = 0;
		while (pos < newContext.length()){
			pos = newContext.indexOf("href=\"", pos);
			if (pos == -1) break;
			int from = pos+6;
			int to = from+7 ;
			if (to<newContext.length() && !newContext.substring(from,to).equals("http://"))
				newContext = newContext.substring(0,from)+"http://"+newContext.substring(from+1);
			pos = to;
		}
		return newContext;
	}

	public int add(Post obj) {
		obj.setContext(xssDefend(obj.getContext()));
		return postmapper.add(obj);
	}

	@Override
	@Caching(evict = {
				@CacheEvict(value="post", key="#obj.keyGen",beforeInvocation = true),
			})
	public Post update(Post obj) {
		obj.setContext(xssDefend(obj.getContext()));
		postmapper.update(obj);
		return 	obj;
	}

	@Override	
	@Caching(evict = {
				@CacheEvict(value="post", key="#obj.keyGen")

	})
	public int delete(Post obj) {
		return postmapper.delete(obj);
	}

	@Override
	@Cacheable(value = "post", key = "#obj.keyGen")
	public Post get(Post obj) {
		return postmapper.get(obj);
	}
	
	@PreDestroy
	@Caching(evict = {
			@CacheEvict(value = "post", allEntries=true)
	})
	public void cleanup() {}

	public boolean check(Post post) {
		return postmapper.check(post);
	}


	public Map<String, List<Object>> getModulesAndCategories(User user) {
		List<Map<String,Object>> temp = postmapper.getModulesAndCategories(user);
		Map<String, List<Object>> result = new TreeMap<String, List<Object>>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj1.compareTo(obj2);
                    } 
                });
		for (Map<String,Object> item: temp) {
			
				List<Object> list = result.get(item.get("module"));
				if (list == null) {
					List<Object> toBePut = new ArrayList<Object>();
					toBePut.add(item.get("category"));
					result.put((String) item.get("module"), toBePut);
				}	else {
					list.add(item.get("category"));
				}
			
			
		}
		return result;
	}
	
}
