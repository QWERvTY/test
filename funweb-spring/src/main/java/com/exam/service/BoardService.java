package com.exam.service;

import java.util.List;

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;

public interface BoardService {
	
	public int insert(BoardVO boardVO);
	
	public int getSeqBoardNum();
	
	public List<BoardVO> getBoards(int pageNum, int amount, String search);
	
	public int getBoardCount(String search);
	
	public int updateReadCount(int num);

	public BoardVO getBoard(int num);
	
	public boolean updateBoard(BoardVO board);
	
	public boolean deleteBoard(int num, String pass);
	
	public void replyInsert(BoardVO board);
	
	public void insertBoardANDAttach(BoardVO board, List<AttachVO> attachList);
	
	public List<AttachVO> findByBNum(int bNum);
	
	public void deleteBoardANDAttach(int num);
	
}
