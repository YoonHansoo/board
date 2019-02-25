package kr.or.ddit.comments.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.comments.dao.CommentDaoImpl;
import kr.or.ddit.comments.dao.ICommentDao;
import kr.or.ddit.comments.model.CommentsVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

public class CommentServiceImpl implements ICommentService {

	private ICommentDao commentDao;

	public CommentServiceImpl() {
		commentDao = new CommentDaoImpl();
	}
	
	@Override
	public int insertComments(CommentsVo commentsVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		int insertCnt = commentDao.insertComments(sqlSession, commentsVo);

		sqlSession.commit();
		sqlSession.close();
		
		return insertCnt;
	}

	@Override
	public List<CommentsVo> selectComments(String postNo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<CommentsVo> commentList= commentDao.selectComments(sqlSession, postNo);

		sqlSession.commit();
		sqlSession.close();
		
		return commentList;
	}

	@Override
	public int delComments(String commentNo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		int delCnt = commentDao.delComments(sqlSession, commentNo);

		sqlSession.commit();
		sqlSession.close();
		
		return delCnt;
	}

}
