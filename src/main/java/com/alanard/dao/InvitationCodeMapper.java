package com.alanard.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.alanard.pojo.InvitationCode;


public interface InvitationCodeMapper extends Mapper<InvitationCode>{
	@Override
	@Insert("INSERT INTO invitationcode (code) VALUES (#{code})")
	@Options(useGeneratedKeys = true)
	public int add(InvitationCode obj);
	
	public boolean update(InvitationCode obj);
		
	@Override
	@Delete("Delete from invitationcode WHERE id = #{id}")
	public int delete(InvitationCode obj);

	public InvitationCode get(InvitationCode obj);
	
	
	@Select("SELECT * from invitationcode WHERE code = #{code}")
	public InvitationCode getByCode(InvitationCode obj);

	@Delete("Delete from invitationcode WHERE code = #{code}")
	public void deleteByCode(InvitationCode code);
}
