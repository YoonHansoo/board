package kr.or.ddit.comments.service;


import org.apache.ibatis.session.SqlSession;
import org.junit.Before;

import kr.or.ddit.comments.dao.CommentDaoImpl;
import kr.or.ddit.comments.dao.ICommentDao;
import kr.or.ddit.comments.model.CommentsVo;

public class CommentServiceImplTest {
	private ICommentDao commentDao;
	
	
	@Before
	public void setUp() throws Exception {
		commentDao = new CommentDaoImpl() {
			
			@Override
			public int insertComments(SqlSession sqlSession, CommentsVo commentsVo) {
				// TODO Auto-generated method stub
				return 0;
			}
		};

	}

	
}
