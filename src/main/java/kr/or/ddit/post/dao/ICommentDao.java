package kr.or.ddit.post.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.util.pageVo;

public interface ICommentDao {
	
		int getPostCnt(SqlSession sqlSession, int boardNo);
		
		
		List<PostVo> selectPostPagingList(SqlSession sqlSession, pageVo pageVo);
		
		int insertPost(SqlSession sqlSession,PostVo postVo);
		
		int insertUploadFile(SqlSession sqlSession, UploadfileVo uploadfileVo); 


		PostVo selectPostDetail(SqlSession sqlSession, String postNo);


		List<UploadfileVo> selectUploadFile(SqlSession sqlSession, String postNo);


		UploadfileVo selectUploadFileOne(SqlSession sqlSession, String uploadFileNo);


		int updPost(SqlSession sqlSession, PostVo postVo);


		void deleteUploadFileAll(SqlSession sqlSession, String postNo);




		void insertUploadFileforUpd(SqlSession sqlSession, UploadfileVo uploadfileVo);


		String selectBoardNm(SqlSession sqlSession, String boardNo);


		int delPost(SqlSession sqlSession, String postNo);
}
