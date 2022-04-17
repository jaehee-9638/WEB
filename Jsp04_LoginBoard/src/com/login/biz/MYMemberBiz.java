package com.login.biz;

import java.util.List;

import com.login.dao.MYMemberDao;
import com.login.dto.MYMemberDto;

public class MYMemberBiz {

	MYMemberDao dao;
	
	public MYMemberBiz() {
		dao = new MYMemberDao();
	}
	
	public List<MYMemberDto> selectAllUser() {
		return dao.selectAllUser();
	}
	
}
