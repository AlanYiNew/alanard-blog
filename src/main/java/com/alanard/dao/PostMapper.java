package com.alanard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alanard.pojo.Post;
import com.alanard.pojo.User;
import com.alanard.utils.Page;

public interface PostMapper extends Mapper<Post>{

    @Select("SELECT * FROM post  WHERE user_id = #{userId} ORDER BY postDate DESC LIMIT #{begin},#{end}")
    @Results(value = {@Result(property = "user", column = "user_id", one=@One(select = "getUser"))})
	List<Post> getPostByPage(Page page);

    
	int getModuleList(Map<String, Object> args);

	int getCategoryList(Map<String, Object> args);

	@Select("SELECT * FROM post  WHERE user_id = #{userId} AND module = #{module} ORDER BY postDate DESC LIMIT #{begin},#{end}")
	@Results(value = {@Result(property = "user", column = "user_id", one=@One(select = "getUser"))})
	List<Post> getPostByModule(Page page);

	@Select("SELECT * FROM post  WHERE user_id = #{userId} AND category = #{category} ORDER BY postDate DESC LIMIT #{begin},#{end}")
	@Results(value = {@Result(property = "user", column = "user_id", one=@One(select = "getUser"))})
	List<Post> getPostByCategory(Page page);

	@Override
	@Insert("INSERT INTO post VALUES("
			+ "#{id},"
			+ "#{title},"
			+ "#{context},"
			+ "#{postDate},"
			+ "#{updateDate},"
			+ "#{category},"
			+ "#{module},"
			+ "#{postCover},"
			+ "#{user.id})")
	@Options(useGeneratedKeys=true)
	public int add(Post obj);
	
	@Override
	@Update("UPDATE post set "
			+ "id = #{id}, "
			+ "title = #{title}, "
			+ "context = #{context}, "
			+ "postDate=#{postDate}, "
			+ "updateDate=#{updateDate}, "
			+ "category = #{category}, "
			+ "module = #{module}, "
			+ "postCover=#{postCover} WHERE id = #{id}")
	public boolean update(Post obj);
		
	@Override
	@Delete("DELETE FROM post WHERE id = #{id}")
	public int delete(Post obj);

	@Override
	@Select("SELECT * FROM post WHERE id = #{id}")
	@Results(value = {@Result(property = "user", column = "user_id", one=@One(select = "getUser"))})
	public Post get(Post obj);
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	public User getUser(User user);

	@Select("SELECT COUNT(1) FROM post WHERE id = #{id} AND user_id = #{user.id}")
	boolean check(Post post);

	@Select("SELECT DISTINCT module, category FROM post WHERE user_id = #{id}")
	List<Map<String, Object>> getModulesAndCategories(User user);
}
