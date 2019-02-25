package kr.or.ddit.comments.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.comments.model.CommentsVo;

public interface ICommentDao {

	int insertComments(SqlSession sqlSession, CommentsVo commentsVo);

	List<CommentsVo> selectComments(SqlSession sqlSession, String postNo);

	int delComments(SqlSession sqlSession, String commentNo);

}
