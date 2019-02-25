package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;

	public interface IBoardDao {
	
	List<BoardVo> getAllBoard(SqlSession sqlSession);

	List<BoardVo> getAllBoardList(SqlSession sqlSession);


	int insertBoard(SqlSession sqlSession, String boarNm);

	int updBoard(SqlSession sqlSession, BoardVo boardVo);
	
}
