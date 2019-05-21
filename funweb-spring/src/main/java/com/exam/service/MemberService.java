package com.exam.service;

import java.util.List;

import com.exam.domain.MemberVO;

public interface MemberService {
	public int register(MemberVO member);
	
	public int countById(String id);
	
	public boolean isIdDupChecked(String id);
	
	public int loginCheck(String id, String pw);
	
	public List<MemberVO> getAllMembers();
	
}
