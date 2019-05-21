package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.domain.MemberVO;
import com.exam.mapper.MemberMapper;

import lombok.Setter;

@Service
public class MemberServiceImpl implements MemberService {
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

//	@Transactional
	@Override
	public int register(MemberVO member) {
		return mapper.insert(member);
	}

	@Override
	public int countById(String id) {
		return mapper.countById(id);
	}
	
	@Override
	public boolean isIdDupChecked(String id) {
		int count = mapper.countById(id);
		
		boolean isDup = false;
		if (count > 0) {
			isDup = true;
		}
		
		return isDup;
	}

	@Override
	public int loginCheck(String id, String pw) {
		int check = -1;
		MemberVO memberVO = mapper.getMemberById(id);
		if (memberVO != null) {
			if (memberVO.getPassword().equals(pw)) {
				check = 1;
			} else {
				check = 0;
			}
		} else {
			check = -1;
		}
		
		return check;
	}

	@Override
	public List<MemberVO> getAllMembers() {
		return mapper.getAllMember();
	}


}
