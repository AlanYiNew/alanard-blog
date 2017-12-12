package com.alanard.services.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanard.dao.InvitationCodeMapper;
import com.alanard.pojo.InvitationCode;
import com.alanard.services.BaseService;


@Service
public class InvitationServiceImpl implements BaseService<InvitationCode> {

	@Autowired
	InvitationCodeMapper invitationCodeMapper;
	
	public InvitationServiceImpl(){
		
	}
	
	public void addCode(String codes) {
		codes = codes.replaceAll(
				"</?[^<>]*/?>",
				"");
		String[] cs = codes.split(" ");
		for (int i = 0; i < cs.length;i++){
			InvitationCode ivc= new InvitationCode();
			ivc.setCode(cs[i]);
			add(ivc);
		}
	}

	@Override
	public int add(InvitationCode obj) {
		return invitationCodeMapper.add(obj);		
	}

	@Override
	public InvitationCode update(InvitationCode obj) {
		invitationCodeMapper.update(obj);	
		return obj;
	}

	@Override
	public int delete(InvitationCode obj) {
		return invitationCodeMapper.delete(obj);
	}

	@Override
	public InvitationCode get(InvitationCode obj) {
		return invitationCodeMapper.get(obj);
	}

	public void cleanup() {}

	public boolean checkInvitaionCode(InvitationCode code) {		
		InvitationCode result = invitationCodeMapper.getByCode(code);
		if (result == null) return false;
		return true;
	}

	public void deleteByCode(InvitationCode code) {
		invitationCodeMapper.deleteByCode(code);		
	}
}
