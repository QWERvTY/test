package com.exam.service;

import java.util.List;

import com.exam.domain.AttachVO;

public interface AttachService {
	
	public void insert(AttachVO attach);
	
	public void insert(List<AttachVO> attachList);

	public void delete(String uuid);
	
	public void deleteByBoardNum(int bNum);
	
	public List<AttachVO> findByBNum(int bNum);
}
