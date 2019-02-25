package kr.or.ddit.comments.service;

import java.util.List;

import kr.or.ddit.comments.model.CommentsVo;

public interface ICommentService {

	int insertComments(CommentsVo commentsVo);

	List<CommentsVo> selectComments(String postNo);

	int delComments(String commentNo);

}
