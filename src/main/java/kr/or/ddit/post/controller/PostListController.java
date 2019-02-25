package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.util.pageVo;

@WebServlet("/postList")
public class PostListController extends HttpServlet {
    private IPostService postService;   
    private Logger logger = LoggerFactory.getLogger(PostListController.class);
	
	
	@Override
	public void init() throws ServletException {
		postService= new PostServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		//String boardNm = request.getParameter("boardNm");
		String str_page = request.getParameter("page");
			
		String boardNm = postService.selectBoardNm(boardNo+"");
		
		int page = str_page==null?1:Integer.parseInt(str_page);
		
		pageVo paging = new pageVo(); //페이징 처리를 위해 페이징 객체 생성 Paging 이라는 VO가 존재함
		paging.setPageNo(page);
		paging.setPageSize(10);
	
		paging.setBoardNo(boardNo); //파라미터로 받은 게시판 번호를 넘긴다.
		List<PostVo> postList = postService.selectPostPagingList(paging);
		
		request.setAttribute("postList", postList);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("post/postList.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
