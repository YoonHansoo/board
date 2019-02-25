package kr.or.ddit.login;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.javafx.scene.web.Debugger;

import javafx.application.Application;
import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserServiceImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	IUserService userService;
	  private IBoardService boardService;  
	private Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
		boardService = new BoardServiceImpl();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		logger.debug("userId {}", userId);
		logger.debug("pass {}", pass);
		
		UserVo userVo  = userService.selectUser(userId);
		
		 if(userVo.getUserId().equals(userId) && userVo.getPass().equals(KISA_SHA256.encrypt(pass))) {  //내가 입력한 password를 암호화 후 equal 비교
			 //사용자 정보를 session에 저장한다.
			 //userVo 객체는 session이 유지될 동안 다른  페이지나 servlet에서 사용할 수 있다.
			 HttpSession session = request.getSession();
			 session.setAttribute("userVo", userVo);
			 

				List<BoardVo> boardList = boardService.getAllBoard();

			//보드 리스트는 계속 가지고 있고 모든 사용자에게 똑같기 때문에 application에 담는다.
			request.getServletContext().setAttribute("boardList", boardList);
			logger.debug("boardList:size : {}", boardList);
				
			 logger.debug("로그인성공");
			 RequestDispatcher rd = request.getRequestDispatcher("module/main.jsp");
			 rd.forward(request, response);
			 
			 
		 }
		 else {
			 logger.debug("로그인실패");
			 RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			 rd.forward(request, response);
		 }
		
		
	}

}
