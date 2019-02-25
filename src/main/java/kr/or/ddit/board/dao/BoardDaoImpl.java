package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVo;

public class BoardDaoImpl implements IBoardDao {

	@Override
	public List<BoardVo> getAllBoard(SqlSession sqlSession) {
		List<BoardVo> boardList = sqlSession.selectList("board.getAllBoard");
		return boardList;
	}

	@Override
	public List<BoardVo> getAllBoardList(SqlSession sqlSession) {
		List<BoardVo> boardList = sqlSession.selectList("board.getAllBoardList");
		return boardList;
	}

	@Override
	public int insertBoard(SqlSession sqlSession, String boarNm) {
		int boardCnt= sqlSession.insert("board.insertBoard", boarNm);
		return boardCnt;
	}

	@Override
	public int updBoard(SqlSession sqlSession, BoardVo boardVo) {
		int boardCnt= sqlSession.insert("board.updBoard", boardVo);
		return boardCnt;
	}

}
