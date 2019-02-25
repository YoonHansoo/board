package kr.or.ddit.post.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.post.dao.ICommentDao;
import kr.or.ddit.post.dao.PostDaoImpl;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.pageVo;

public class PostServiceImpl implements IPostService {

	private ICommentDao postDao;

	public PostServiceImpl() {
		postDao = new PostDaoImpl();
	}

	@Override
	public List<PostVo> selectPostPagingList(pageVo pageVo) {

		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		int totalCnt = postDao.getPostCnt(sqlSession, pageVo.getBoardNo());
		pageVo.setTotalCount(totalCnt);

		List<PostVo> postList = new ArrayList<>();
		postList = postDao.selectPostPagingList(sqlSession, pageVo);

		sqlSession.close();
		return postList;
	}

	@Override
	public int insertPostUpload(PostVo postVo, List<UploadfileVo> uploadList) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post insert
		int insertCnt = postDao.insertPost(sqlSession, postVo);

		// uploadfile을 for문으로 인서트

		for (UploadfileVo uploadfileVo : uploadList) {
			postDao.insertUploadFile(sqlSession, uploadfileVo);

		}

		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public PostVo selectPostDetail(String postNo) {

		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post insert
		PostVo postVo = postDao.selectPostDetail(sqlSession, postNo);

		sqlSession.commit();
		sqlSession.close();
		return postVo;

	}

	@Override
	public List<UploadfileVo> selectUploadFile(String postNo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post insert
		List<UploadfileVo> uploadFileList = postDao.selectUploadFile(sqlSession, postNo);

		sqlSession.commit();
		sqlSession.close();

		return uploadFileList;
	}

	@Override
	public UploadfileVo selectUploadFileOne(String uploadFileNo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post insert
		UploadfileVo uploadFileOne = postDao.selectUploadFileOne(sqlSession, uploadFileNo);

		sqlSession.commit();
		sqlSession.close();

		return uploadFileOne;

	}

	@Override
	public int updPostUpload(PostVo postVo, List<UploadfileVo> uploadList) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post 업데이트 쿼리
		int updCnt = postDao.updPost(sqlSession, postVo);

		// 첨부파일을 전부 지워주는 쿼리
		postDao.deleteUploadFileAll(sqlSession, postVo.getPostNo());

		// 새로운 첨부파일을 다시 추가하는 쿼리
		for (UploadfileVo uploadfileVo : uploadList) {
			postDao.insertUploadFileforUpd(sqlSession, uploadfileVo);
		}
		sqlSession.commit();
		sqlSession.close();

		return 1;

	}

	@Override
	public String selectBoardNm(String boardNo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post insert
		String boardNm = postDao.selectBoardNm(sqlSession, boardNo);

		sqlSession.commit();
		sqlSession.close();

		return boardNm;
	}

	@Override
	public int delPost(String postNo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// post insert
		int delpostCnt = postDao.delPost(sqlSession, postNo);

		sqlSession.commit();
		sqlSession.close();
		return delpostCnt;
	}

}
