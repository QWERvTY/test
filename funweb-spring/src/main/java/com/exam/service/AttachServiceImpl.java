package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AttachVO;
import com.exam.mapper.AttachMapper;

import lombok.Setter;

@Service
public class AttachServiceImpl implements AttachService {
	
	@Setter(onMethod_ = @Autowired)
	private AttachMapper attachMapper;

	@Override
	public void insert(AttachVO attach) {
		attachMapper.insert(attach);
	}

	@Transactional
	@Override
	public void insert(List<AttachVO> attachList) {
		for (AttachVO attachVO : attachList) {
			attachMapper.insert(attachVO);
		}
	}

	@Override
	public void delete(String uuid) {
		attachMapper.delete(uuid);
	}

	@Override
	public void deleteByBoardNum(int bNum) {
		attachMapper.deleteByBoardNum(bNum);
	}

	@Override
	public List<AttachVO> findByBNum(int bNum) {
		return attachMapper.findByBNum(bNum);
	}

}
