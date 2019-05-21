package com.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.domain.AttachVO;
import com.exam.domain.BoardVO;
import com.exam.mapper.AttachMapper;
import com.exam.mapper.BoardMapper;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Setter(onMethod_ = @Autowired)
	private AttachMapper attachMapper;

	@Override
	public int getSeqBoardNum() {
		return boardMapper.getSeqBoardNum();
	}
	
	@Override
	public int insert(BoardVO boardVO) {
		return boardMapper.insertBoard(boardVO);
	}
	
	@Override
	public List<BoardVO> getBoards(int pageNum, int amount, String search) {
		return boardMapper.getBoards(pageNum, amount, search);
	}

	@Override
	public int getBoardCount(String search) {
		return boardMapper.getBoardCount(search);
	}

	@Override
	public int updateReadCount(int num) {
		return boardMapper.updateReadCount(num);
	}

	@Override
	public BoardVO getBoard(int num) {
		return boardMapper.getBoard(num);
	}

	@Override
	public boolean updateBoard(BoardVO board) {
		boolean isSuccess = false;
		BoardVO dbBoard = boardMapper.getBoard(board.getNum());
		if (board.getPass().equals(dbBoard.getPass())) {
			boardMapper.updateBoard(board);
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		
		return isSuccess;
	}

	@Override
	public boolean deleteBoard(int num, String pass) {
		boolean isSuccess = false;
		BoardVO board = boardMapper.getBoard(num);
		if (pass.equals(board.getPass())) {
			boardMapper.deleteBoard(num);	
			isSuccess = true;
		} else {
			isSuccess = false;
		}
		
		return isSuccess;
	}

	@Transactional
	@Override
	public void replyInsert(BoardVO board) {
		boardMapper.updateReplyGroupSeq(board.getReRef(), board.getReSeq());
		
		int num = boardMapper.getSeqBoardNum();
		board.setNum(num);
		
		// re_lev는 "답글을 다는 대상글"의 들여쓰기 값 + 1
		board.setReLev(board.getReLev() + 1);
		// re_seq는 "답글을 다는 대상글"의 글 그룹 내 순번값 + 1
		board.setReSeq(board.getReSeq() + 1);
	
		boardMapper.insertBoard(board);
	}

	@Transactional
	@Override
	public void insertBoardANDAttach(BoardVO board, List<AttachVO> attachList) {
		boardMapper.insertBoard(board); // 파일 게시판 주글 등록
		
		if (attachList.size() > 0) { // 업로드 파일이 있는 경우에만
			for (AttachVO attachVO : attachList) {
				attachMapper.insert(attachVO); // 첨부파일 정보 등록
			}
		}
		
	}

	@Override
	public List<AttachVO> findByBNum(int bNum) {
		return attachMapper.findByBNum(bNum);
	}

	@Transactional
	@Override
	public void deleteBoardANDAttach(int num) {
		boardMapper.deleteBoard(num);
		attachMapper.deleteByBoardNum(num);
		
		
	}

	
	
	
	
	
	
	

	

}
