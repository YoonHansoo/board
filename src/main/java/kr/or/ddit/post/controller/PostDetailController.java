package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.comments.model.CommentsVo;
import kr.or.ddit.comments.service.CommentServiceImpl;
import kr.or.ddit.comments.service.ICommentService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/postDetail")
public class PostDetailController extends HttpServlet {
       
	private Logger logger = LoggerFactory.getLogger(PostDetailController.class);
	
	IPostService postService;
	ICommentService commentService;

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		commentService = new CommentServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNo = request.getParameter("postNo");
		String post_Gn = request.getParameter("post_Gn");
		String boardNo = request.getParameter("boardNo");
		String boardNm = postService.selectBoardNm(boardNo);
		
		Map<String,String> infoMap = new HashMap<>();
		infoMap.put("postNo", postNo);
		infoMap.put("post_Gn", post_Gn);
		infoMap.put("boardNm", boardNm);
		infoMap.put("boardNo", boardNo);
		
		logger.debug("postNo {}", postNo);
		logger.debug("post_Gn {}", post_Gn);
		logger.debug("boardNm {}", boardNm);
		logger.debug("boardNo {}", boardNo);
	
		
		//postNo로 값을 조회하는 쿼리
		PostVo postVo = postService.selectPostDetail(postNo);
		
		//postNo에 해당하는 댓글조회 쿼리
		List<CommentsVo> commentList = commentService.selectComments(postNo);
		
		//postNo로 첨부파일을 조회하는 쿼리
		 List<UploadfileVo> uploadFileList = postService.selectUploadFile(postNo);
		
		request.setAttribute("infoMap", infoMap);
		request.setAttribute("postVo", postVo);
		request.setAttribute("uploadFileList", uploadFileList);
		request.setAttribute("commentList", commentList);
		
		request.getRequestDispatcher("/post/postDetail.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
