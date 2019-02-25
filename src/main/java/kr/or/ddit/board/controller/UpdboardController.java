package kr.or.ddit.board.controller;

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

@WebServlet("/updboard")
public class UpdboardController extends HttpServlet {
	  private IBoardService boardService;   
	    private Logger logger = LoggerFactory.getLogger(BoardAllListController.class);
		
		
		@Override
		public void init() throws ServletException {
			boardService = new BoardServiceImpl();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardFlag = request.getParameter("boardFlag");
		String boardNo = request.getParameter("boardNo");
	
		BoardVo boardVo = new BoardVo();
		boardVo.setBoardNo(boardNo);
		boardVo.setBoardFlag(boardFlag);
		
		int boardCnt = boardService.updBoard(boardVo);
		
	/*	
		List<BoardVo> boardList = boardService.getAllBoard(); //left의 메뉴를 갱신하기위해서 다시 받아옴
		request.getServletContext().removeAttribute("boardList");
		request.getServletContext().setAttribute("boardList", boardList);*/
		
		response.sendRedirect(request.getContextPath()+"/boardList");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	}

}
