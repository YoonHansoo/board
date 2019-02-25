package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.util.PartUtil;

@WebServlet("/postForm")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PostFormController extends HttpServlet {

	private static final String UPLOAD_PATH = "d:\\picture\\";
	private Logger logger = LoggerFactory.getLogger(PostFormController.class);
	
	IPostService postService;

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String boardNo = request.getParameter("boardNo");
		String post_Gn = request.getParameter("post_Gn");
		String postNo = request.getParameter("postNo");
		
		String boardNm = postService.selectBoardNm(boardNo);
		
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("post_Gn", post_Gn);
		request.setAttribute("postNo", postNo);
		request.getRequestDispatcher("/post/postForm.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String boardNo = request.getParameter("boardNo");
		String userId = request.getParameter("userId");
		String postTitle = request.getParameter("postTitle");
		String smarteditor = request.getParameter("smarteditor");
		String post_Gn = request.getParameter("post_Gn");
		String flag = request.getParameter("flag");
		
		String postNo = request.getParameter("postNo"); //답글 때문에
		logger.debug("postNo {}", postNo);
		logger.debug("post_Gn {}", post_Gn);
		logger.debug("flag {}", flag);

		PostVo postVo = new PostVo();
		postVo.setBoardNo(boardNo);
		postVo.setPostContent(smarteditor);
		postVo.setPostTitle(postTitle);
		postVo.setUserId(userId);
		
		// parent_Postno가 없으면 post_Gn에 자기자신의 값을 추가해야 한다.
		// postVo.setParent_Postno(parent_Postno); 나중에 추가해야함 if문으로 추가하자.
		//답글일 떄
		if(flag.equals("T")) {
			postVo.setParent_Postno(postNo);
			postVo.setPost_Gn(post_Gn);
		}

		List<UploadfileVo> uploadList = new ArrayList<>();

		Collection<Part> parts = request.getParts();
		for (Part part : parts) {
			String filename = "";
			String realFilename = "";

			if (!part.getName().equals("upload")) { // 업로드 파일이 아닌 것들은 패스
				continue;
			}
			// 올린 파일이 존재할 때
			if (part.getSize() > 0) {
				String contentdisposition = part.getHeader("Content-Disposition");

				filename = PartUtil.getFileNameFromPart(contentdisposition);
				realFilename = UPLOAD_PATH + UUID.randomUUID().toString(); // DB에 전체경로로 저장되어야 하기 떄문에

				UploadfileVo uploadfileVo = new UploadfileVo();
				uploadfileVo.setUploadRealFilePath(realFilename);
				uploadfileVo.setUploadFileName(filename);

				uploadList.add(uploadfileVo);

				part.write(realFilename); // 디스크에 기록
			}
		}
		logger.debug("uploadSize {} 개 ", uploadList.size());
		// 전체 인서트 쿼리
		postService.insertPostUpload(postVo ,uploadList);
		
		request.getSession().setAttribute("msg", "새글이 작성되었습니다.");
		
		response.sendRedirect(request.getContextPath()+"/postList?boardNo="+boardNo);

	}
}