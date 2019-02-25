package kr.or.ddit.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.CommentsVo;

public class CommentDaoImpl implements ICommentDao  {

	@Override
	public int insertComments(SqlSession sqlSession, CommentsVo commentsVo) {
		int insertCnt = sqlSession.insert("comments.insertComments",commentsVo);
		return insertCnt;
	}

	@Override
	public List<CommentsVo> selectComments(SqlSession sqlSession, String postNo) {
		List<CommentsVo> commentList = sqlSession.selectList("comments.selectComments",postNo);
		return commentList;
	}

	@Override
	public int delComments(SqlSession sqlSession, String commentNo) {
		int delCnt= sqlSession.update("comments.delComments",commentNo);
		return delCnt;
	}


}
