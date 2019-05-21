package com.exam.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data // 컴파일로 GETTER, SETTER가 자동 생성
public class MemberVO {
	private String id, password, name, email, address, tel, mTel, gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regDate, birthday;

	
}
