package kr.or.ddit.post.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.sun.org.apache.regexp.internal.recompile;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.util.pageVo;

public class PostDaoImpl implements ICommentDao {

	@Override
	public int getPostCnt(SqlSession sqlSession,int boardNo) {
		int postCnt = sqlSession.selectOne("post.getPostCnt", boardNo);
		return postCnt;
	}

	@Override
	public List<PostVo> selectPostPagingList(SqlSession sqlSession, pageVo pageVo) {
		List<PostVo> list = sqlSession.selectList("post.selectPostPagingList", pageVo); 
		return list;
	}

	@Override
	public int insertPost(SqlSession sqlSession, PostVo postVo) {
		int insertCnt = sqlSession.insert("post.insertPost", postVo);
		return insertCnt;
	}

	@Override
	public int insertUploadFile(SqlSession sqlSession, UploadfileVo uploadfileVo) {
		int insertCnt = sqlSession.insert("post.insertUploadFile", uploadfileVo);
		return insertCnt;
	}

	@Override
	public PostVo selectPostDetail(SqlSession sqlSession, String postNo) {
		PostVo postVo = sqlSession.selectOne("post.selectPostDetail", postNo );
		return postVo;
	}

	@Override
	public List<UploadfileVo> selectUploadFile(SqlSession sqlSession, String postNo) {
		List<UploadfileVo> list = sqlSession.selectList("post.selectUploadFile", postNo);
		return list;
	}

	@Override
	public UploadfileVo selectUploadFileOne(SqlSession sqlSession, String uploadFileNo) {
		UploadfileVo UploadfileOne = sqlSession.selectOne("post.selectUploadFileOne", uploadFileNo); 
		return UploadfileOne;
	}

	@Override
	public int updPost(SqlSession sqlSession, PostVo postVo) {
		int updCnt = sqlSession.update("post.updPost", postVo); 
		return updCnt;
	}

	@Override
	public void deleteUploadFileAll(SqlSession sqlSession, String postNo) {
		sqlSession.delete("post.deleteUploadFileAll", postNo);
	}

	@Override
	public void insertUploadFileforUpd(SqlSession sqlSession, UploadfileVo uploadfileVo) {
		int insertCnt = sqlSession.insert("post.insertUploadFileforUpd", uploadfileVo);
	}

	@Override
	public String selectBoardNm(SqlSession sqlSession, String boardNo) {
		String boardNm = sqlSession.selectOne("post.selectBoardNm", boardNo);
		return boardNm;
	}

	@Override
	public int delPost(SqlSession sqlSession, String postNo) {
		int delpostCnt = sqlSession.update("post.delPost", postNo);
		return delpostCnt;
	}


}
