package com.alanard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.alanard.pojo.User;
import com.alanard.utils.Page;

public interface UserMapper extends Mapper<User>{
	public User getUserByUsername(User user);

	public void changePassword(User user);

	@Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
	public User authenticateUser(User user);

	
	@Override
	@Insert("INSERT INTO user"
			+ "(username, "
			+ "password, "
			+ "email) "
			+ "VALUES("
			+ "#{username}, "
			+ "#{password}, "
			+ "#{email})")
	@Options(useGeneratedKeys=true)
	public int add(User obj);
	
	@Override
	@Update("UPDATE user SET "
			+ "username = #{username}, "
			+ "password = #{password}, "
			+ "pofolioPic = #{pofolioPic}, "
			+ "introduction = #{introduction}, "
			+ "background = #{background} WHERE id = #{id}")
	public boolean update(User obj);
		
	@Override
	@Delete("DELETE FROM user WHERE id = #{id}")
	public int delete(User obj);

	@Override
	@Select("SELECT * FROM user WHERE id = #{id}")
	public User get(User obj);

	@Select("SELECT * FROM user ORDER BY readings DESC LIMIT #{begin},#{end}")
	public List<User> getUserListByReadings(Page page);


	@Select("SELECT * FROM user WHERE email = #{email}")
	public User getUserByEmail(User user);

	@Update("UPDATE user SET "
			+ "password = #{password} "
			+ "WHERE email = #{email}")
	public void resetPasswordByEmail(User user);
}
