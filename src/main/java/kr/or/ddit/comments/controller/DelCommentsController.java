package kr.or.ddit.comments.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.comments.service.CommentServiceImpl;
import kr.or.ddit.comments.service.ICommentService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/delComments")
public class DelCommentsController extends HttpServlet {
       
	private Logger logger = LoggerFactory.getLogger(DelCommentsController.class);
	ICommentService commentService;

	
	@Override
	public void init() throws ServletException {
		commentService = new CommentServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String postNo = request.getParameter("postNo");
		String post_Gn = request.getParameter("post_Gn");
		String boardNo = request.getParameter("boardNo");
		String commentNo = request.getParameter("commentNo");
		
		//삭제쿼리
		int delCnt = commentService.delComments(commentNo);
		
		 request.getSession().setAttribute("msg", "댓글이 정상적으로 삭제되었습니다.");
		response.sendRedirect(request.getContextPath()+"/postDetail?postNo="+postNo+"&post_Gn="+post_Gn+"&boardNo="+boardNo);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
