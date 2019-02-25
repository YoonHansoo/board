package kr.or.ddit.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
		
	/**
	 * Method : getAllBoard
	 * 작성자 : Hansoo
	 * 변경이력 :
	 * @return
	 * Method 설명 :모든 게시판을  조회하는 쿼리
	 */
	@Override

	public List<BoardVo> getAllBoard() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		 List<BoardVo> boardlist = boardDao.getAllBoard(sqlSession);
		sqlSession.commit();
		sqlSession.close();
		return boardlist;
	}

	@Override
	public List<BoardVo> getAllBoardList() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		 List<BoardVo> boardlist = boardDao.getAllBoardList(sqlSession);
		sqlSession.commit();
		sqlSession.close();
		return boardlist;
	}

	@Override
	public int insertBoard(String boarNm) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		 int boardCnt= boardDao.insertBoard(sqlSession, boarNm);
		sqlSession.commit();
		sqlSession.close();
		return boardCnt;
	}

	@Override
	public int updBoard(BoardVo boardVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		 int boardCnt= boardDao.updBoard(sqlSession, boardVo);
		sqlSession.commit();
		sqlSession.close();
		return boardCnt;
	}

}
