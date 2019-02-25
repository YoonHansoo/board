package kr.or.ddit.post.service;

import java.util.List;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.util.pageVo;


public interface IPostService {

	
	List<PostVo> selectPostPagingList(pageVo pageVo);
	
	int insertPostUpload( PostVo postVo ,List<UploadfileVo> uploadList);

	PostVo selectPostDetail(String postNo);

	List<UploadfileVo> selectUploadFile(String postNo);

	UploadfileVo selectUploadFileOne(String uploadFileNo);

	int updPostUpload(PostVo postVo, List<UploadfileVo> uploadList);

	String selectBoardNm(String boardNo);

	int delPost(String postNo);
	
	
}
