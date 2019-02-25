package kr.or.ddit.comments.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.comments.model.CommentsVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class CommentDaoImplTest {
	private ICommentDao commentDao;
	private SqlSession sqlSession;
	
	@Before
	public void setUp() throws Exception {
		commentDao = new CommentDaoImpl();

		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}
	

	@Test
	public void 댓글등록() {
		/***Given***/
		CommentsVo commentsVo = new CommentsVo();
		commentsVo.setCommentContent("내용");
		commentsVo .setUserId("brown");
		commentsVo .setPostNo("12");
		

		/***When***/
		int insertCnt = commentDao.insertComments(sqlSession, commentsVo);
		/***Then***/
		assertEquals(insertCnt, 1);
	}
	
	@Test
	public void 댓글조회() {
		/***Given***/
		String postNo ="1280";
		

		/***When***/
		List<CommentsVo> listCnt = commentDao.selectComments(sqlSession, postNo);
		/***Then***/
		assertEquals(listCnt.size(), 3);
	}

}
