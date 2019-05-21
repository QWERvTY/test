package com.exam.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int num, reRef, reLev, reSeq, readCount;
	// 글 번호, 글 그룹 번호, 글 들여쓰기(답글) 레벨, 글 그룹 내에서의 순서, 조회수 
	private String name, pass, subject, content, ip;
	// 작성자 명, 글 암호, 글 제목, 글 내용, 글에 업로드한 파일명, 작성자 IP 주소
	private Date regDate;
	// 작성 시간
		
	
	
}
