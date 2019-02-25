package kr.or.ddit.comments.controller;

import java.io.IOException;

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
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/createComment")
public class CreateCommentController extends HttpServlet {
	IPostService postService;
	ICommentService commentService;
	private Logger logger = LoggerFactory.getLogger(CreateCommentController.class);
	
	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
		commentService = new CommentServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		logger.debug("댓글작성서블릿");
		String commentContent = request.getParameter("commentContent");
		String userId = request.getParameter("userId");
		String boardNo = request.getParameter("boardNo");
		String postNo = request.getParameter("postNo");
		String post_Gn = request.getParameter("post_Gn");
		
		
		logger.debug("postNo{}",postNo);
		logger.debug("post_Gn{}",post_Gn);
		logger.debug("boardNo{}",boardNo);
		logger.debug("postNo{}",postNo);
		
		logger.debug("commentContent{}", commentContent);
		logger.debug("userId{}", userId);

		CommentsVo commentsVo = new CommentsVo();
		commentsVo.setCommentContent(commentContent);
		commentsVo .setUserId(userId);
		commentsVo .setPostNo(postNo);

		 int insertCnt= commentService.insertComments(commentsVo);
		
		
		 response.sendRedirect(request.getContextPath()+"/postDetail?boardNo="+boardNo+"&post_Gn="+post_Gn+"&postNo="+postNo);

		 
		
	
	}

}
