package kr.or.ddit.post.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.util.pageVo;

public class PostServiceImplTest {
	private IPostService postService;
	
	
	@Before
	public void setUp() throws Exception {
		postService = new PostServiceImpl();

	}

	@Test
	public void selectLprodPagingListTest() {
		/***Given***/
		pageVo pageVo = new pageVo();
		pageVo.setPageNo(1);
		pageVo.setBoardNo(1);
		
		/***When***/
		List<PostVo> selectPostPagingList = postService.selectPostPagingList(pageVo);
		
		
		for(PostVo postVo : selectPostPagingList) {
			System.out.println(postVo.getPostTitle());
			System.out.println(postVo.getPostNo());
			System.out.println(postVo.getUserId());
			System.out.println(postVo.getPost_Reg_Dt());
			System.out.println(postVo.getPost_Reg_Dt());
			System.out.println(postVo.getLevel());
		}
		/***Then***/
		assertEquals(selectPostPagingList.size(), 10);
		
	}

	
	@Test
	public void 인서트파일업로드테스트() {
		/***Given***/
		PostVo postVo = new PostVo();
		postVo.setBoardNo("1");
		postVo.setPostContent("테스트내용123");
		postVo.setPostTitle("테스트 제목123");
		postVo.setUserId("brown");
		
		
		List<UploadfileVo> uploadList = new ArrayList<>();
		
		UploadfileVo uploadfileVo = new UploadfileVo();
		uploadfileVo.setPostNo("10");
		uploadfileVo.setUploadFileNo("nike135566766");
		uploadfileVo.setUploadRealFilePath("adasd");
		uploadList.add(uploadfileVo);

		/***When***/
		int cnt = postService.insertPostUpload(postVo, uploadList);
		
		/***Then***/
		assertEquals(cnt, 1);
		
	}
	@Test
		public void 업로트파일한개조회() {
			/***Given***/
			
			String no = "1240";
			

			/***When***/
			UploadfileVo selectUploadFileOne = postService.selectUploadFileOne(no);
			System.out.println(selectUploadFileOne.toString());
			/***Then***/
			
			assertNotNull(selectUploadFileOne);
		}
}
