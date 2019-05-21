package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.exam.domain.BoardVO;

public interface BoardMapper {
	
	public int getSeqBoardNum();
	
	public int insertBoard(BoardVO board);
	
	public List<BoardVO> getBoards(@Param("pageNum") int pageNum, @Param("amount") int amount, @Param("search") String search);

//	@Select("SELECT COUNT(*) FROM board")
	public int getBoardCount(@Param("search") String search);
	
//	@Update("UPDATE board SET readCount = readCount + 1 WHERE num = #{num}")
	public int updateReadCount(int num);
	
//	@Select("SELECT * FROM board WHERE num = #{num}")
	public BoardVO getBoard(int num);
	
	public int updateBoard(BoardVO board);
	
	public int deleteBoard(int num);
	
	public int updateReplyGroupSeq(@Param("reRef") int reRef, @Param("reSeq") int reSeq);
}
