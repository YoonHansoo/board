package kr.or.ddit.post.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.sun.media.jfxmedia.logging.Logger;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;

public class PostDaoImplTest {
	private ICommentDao postDao;
	private SqlSession sqlSession;
	
	@Before
	public void setUp() throws Exception {
		postDao = new PostDaoImpl();

		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}

	@Test
	public void 첨부파일인서트다오테스트() {
		/***Given***/
		UploadfileVo uploadfileVo = new UploadfileVo();
		uploadfileVo.setPostNo("10");
		uploadfileVo.setUploadFileName("nike135566766");
		uploadfileVo.setUploadRealFilePath("sfsdfa");

		/***When***/
		int cnt = postDao.insertUploadFile(sqlSession, uploadfileVo);

		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void 게시글상세보기조회() {
		/***Given***/
			String postNo =  "10";
		
			

		/***When***/
			PostVo selectPostDetail = postDao.selectPostDetail(sqlSession, postNo);
			System.out.println(selectPostDetail.toString()); 
			
			
		/***Then***/
			
	}
	
	@Test
	public void 첨부파일조회() {
		/***Given***/
		String postNo = "1230";
		

		/***When***/
		List<UploadfileVo> selectUploadFile = postDao.selectUploadFile(sqlSession, postNo);
		

		/***Then***/
		assertEquals(selectUploadFile.size(), 1);
	}
	
	@Test
	public void 게시글수정() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setPostTitle("test1");
		postVo.setPostContent("test2");
		postVo.setPostNo("10");
		

		/***When***/
		int updCnt = postDao.updPost(sqlSession, postVo);
		

		/***Then***/
		assertEquals(updCnt, 1);
	}
	
	@Test
	public void 게시글에딸린첨부파일전부삭제() {
		/***Given***/
		String postNo = "1220";
		

		/***When***/
		
		postDao.deleteUploadFileAll(sqlSession, postNo);

		/***Then***/
		
	}
	
	@Test
	public void 수정할때첨부파일인서트() {
		UploadfileVo upload = new UploadfileVo();
		upload.setUploadRealFilePath("123");
		upload.setUploadFileName("sdsf");
		upload.setPostNo("12");
		
		
		postDao.insertUploadFileforUpd(sqlSession, upload);
	}

}
