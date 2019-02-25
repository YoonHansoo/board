package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class BoardDaoImplTest {
	private IBoardDao boardDao;
	private SqlSession sqlSession;
	
	@Before
	public void setUp() throws Exception {
		boardDao = new BoardDaoImpl();

		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}

	@Test
	public void getAllBoard() {
		/***Given***/
		

		/***When***/
		List<BoardVo> allBoard = boardDao.getAllBoard(sqlSession);
	

		/***Then***/
		assertEquals(allBoard.size(), 3);
	}

}
