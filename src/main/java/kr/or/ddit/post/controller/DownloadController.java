package kr.or.ddit.post.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.post.model.UploadfileVo;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.post.service.PostServiceImpl;

@WebServlet("/download")
public class DownloadController extends HttpServlet {
	IPostService postService;

	@Override
	public void init() throws ServletException {
		postService = new PostServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uploadFileNo = request.getParameter("uploadFileNo");
		UploadfileVo uploadFileVo = postService.selectUploadFileOne(uploadFileNo);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename ="+uploadFileVo.getUploadFileName());
		FileInputStream fis;
		fis = new FileInputStream(new File(uploadFileVo.getUploadRealFilePath()));
	
		//4.FileInputStream을 response객체의 outputStream 객체에 write
		ServletOutputStream sos = response.getOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fis.read(buff))> -1) {
			sos.write(buff);
		}
		sos.flush();
		sos.close();
		fis.close();
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
