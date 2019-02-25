package kr.or.ddit.post.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/postDel")
public class PostDelController extends HttpServlet {
	private Logger logger = LoggerFactory.getLogger(PostUpdController.class);
	
	IPostService postService;

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardNo = request.getParameter("boardNo");
		String postNo = request.getParameter("postNo");
		
		String boardNm = postService.selectBoardNm(boardNo);
		
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardNm", boardNm);
		//request.setAttribute("post_Gn", post_Gn);
		request.setAttribute("postNo", postNo);
		
		
		int cnt= postService.delPost(postNo);
		if(cnt == 1) {
			logger.debug("삭제성공");
		}
		
		response.sendRedirect(request.getContextPath()+"/postList?boardNo="+boardNo);;
	}

}
