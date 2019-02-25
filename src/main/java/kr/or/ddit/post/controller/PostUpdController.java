package kr.or.ddit.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.media.jfxmedia.track.Track.Encoding;
import com.sun.org.apache.xml.internal.resolver.helpers.Debug;

import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;
import kr.or.ddit.util.PartUtil;

@WebServlet("/postUpd")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PostUpdController extends HttpServlet {
	private static final String UPLOAD_PATH = "d:\\picture\\";
	private Logger logger = LoggerFactory.getLogger(PostUpdController.class);
	
	IPostService postService;

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardNo = request.getParameter("boardNo");
		String postNo = request.getParameter("postNo");
		String post_Gn = request.getParameter("post_Gn");
		
		PostVo postVo = postService.selectPostDetail(postNo);
		String boardNm = postService.selectBoardNm(boardNo);
		logger.debug("게시판이름{}",boardNm);
		
		//postNO로 첨부파일의 데이터를 조회
		List<UploadfileVo> uploadList = postService.selectUploadFile(postNo);
		logger.debug("첨부파일갯수: {}", uploadList.size());
		logger.debug("uploadList: {}", uploadList);
		
		request.setAttribute("uploadList", uploadList);
		request.setAttribute("postVo", postVo);
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardNm", boardNm);
		request.setAttribute("post_Gn", post_Gn);
		request.setAttribute("postNo", postNo);
		
		request.getRequestDispatcher("/post/postUpd.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			
			String postTitle = request.getParameter("postTitle");
			String smarteditor = request.getParameter("smarteditor");
			String postNo = request.getParameter("postNo");
			String boardNm = request.getParameter("boardNm");
			String boardNo = request.getParameter("boardNo");
			String post_Gn = request.getParameter("post_Gn");
			
			PostVo postVo = new PostVo();
			postVo.setPostTitle(postTitle);
			postVo.setPostContent(smarteditor);
			postVo.setPostNo(postNo);
			
			logger.debug("포스트게시판이름{}",boardNm);
			logger.debug("postNo입니다. {}", postNo);
			List<UploadfileVo> uploadList = new ArrayList<>();
			
			//기존에 있던 파일
			String[] uploadFileName = request.getParameterValues("uploadFileName");
			String[] uploadRealFilePath = request.getParameterValues("uploadRealFilePath");
			
			
			if(!(uploadFileName == null)) {
			for(int i = 0; i< uploadFileName.length; i++) {
				UploadfileVo uploadfileVo = new UploadfileVo();
				uploadfileVo.setUploadFileName(uploadFileName[i]);
				uploadfileVo.setPostNo(postNo);
				uploadfileVo.setUploadRealFilePath(uploadRealFilePath[i]);
				uploadList.add(uploadfileVo);
				}
			}
			//만약 수정할 떄 추가로 파일을 넣었을 경우
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
					uploadfileVo.setPostNo(postNo);

					uploadList.add(uploadfileVo);

					part.write(realFilename); // 디스크에 기록
				}
			}
			logger.debug("최종 첨부파일 갯수{}", uploadList.size());
			
			 postService.updPostUpload(postVo, uploadList);
			 
			 request.getSession().setAttribute("msg", "글이 정상적으로 수정되었습니다.");

			response.sendRedirect(request.getContextPath()+"/postDetail?postNo="+postNo+"&post_Gn="+post_Gn+"&boardNo="+boardNo);
		
	}

}
