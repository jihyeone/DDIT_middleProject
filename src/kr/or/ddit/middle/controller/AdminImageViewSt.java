package kr.or.ddit.middle.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/adminImageViewSt.do")
public class AdminImageViewSt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String studID = request.getParameter("stuID");
		
		IAdminService service = AdminServiceImpl.getInstance();
		
		StudentVO stuvo = service.getStudentAd(studID);
		
		String fileName = stuvo.getStu_poto();
		if(fileName == null) {
			fileName = "noImage.png";
		}
		String imagePath = "D:/A_TeachingMaterial/03_HighJava/workspace/dditMiddlePJ/WebContent/images";
		
		String imageFilePath = imagePath + File.separator + fileName;
		
		File file = new File(imageFilePath);
		
		if(file.exists()) {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			
			byte[] buffer = new byte[1024];
			int len = -1;
			
			while((len = bis.read(buffer)) > 0) {
				bos.write(buffer , 0 , len);
			}
			bos.flush();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
