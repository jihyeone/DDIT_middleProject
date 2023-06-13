package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/updateStudent.do")
@MultipartConfig
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String stu_id = request.getParameter("stu_id");
		
		IAdminService service = AdminServiceImpl.getInstance();
		StudentVO stuVo = service.getStudentAd(stu_id);
		request.setAttribute("stuVo", stuVo);
		
		
		request.setAttribute("viewPage", "/WEB-INF/view/MainAdmin/StudentUpdateForm.jsp" );
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "D:/A_TeachingMaterial/03_HighJava/workspace/dditMiddlePJ/WebContent/images";
		
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		request.setCharacterEncoding("utf-8");
		String stu_id = request.getParameter("stu_id");
		String stu_nm = request.getParameter("stu_nm"); 
		String stu_reg = request.getParameter("stu_reg"); 
		String stu_pw = request.getParameter("stu_pw");
		String stu_addr = request.getParameter("stu_addr"); 
		String stu_mail = request.getParameter("stu_mail"); 
		String stu_tel = request.getParameter("stu_tel"); 
		String stu_zip = request.getParameter("stu_zip");
		String class_no = request.getParameter("class_no");
		String stu_poto = request.getParameter("stu_poto");
		String stu_fin = request.getParameter("stu_fin");
		String stu_drop = request.getParameter("stu_drop");
		
		
		StudentVO stuVo = new StudentVO();
		stuVo.setStu_id(stu_id);
		stuVo.setStu_nm(stu_nm); 
		stuVo.setStu_reg(stu_reg); 
		stuVo.setStu_pw(stu_pw);
	    stuVo.setStu_addr(stu_addr);
		stuVo.setStu_mail(stu_mail);
		stuVo.setStu_tel(stu_tel);
		stuVo.setClass_no(class_no);
		stuVo.setStu_zip(stu_zip);
		stuVo.setStu_poto(stu_poto);
		
		if(stu_fin.equals("수강중")) {
			stuVo.setStu_fin("N");
		} else {
			stuVo.setStu_fin("Y");
			
		}
		
		if(stu_drop.equals("수강중")) {
			stuVo.setStu_drop("N");
		} else {
			stuVo.setStu_drop("Y");
		}
		
		  Part part = request.getPart("stu_poto");
		 
		 if(part != null) {
			 String poto = extractFileName(part); 
			 if(!"".equals(poto))		 {
				 try {
					 String savePoto = UUID.randomUUID().toString();
					 part.write(uploadPath + File.separator + savePoto); 
					 stuVo.setStu_poto(savePoto);
				 } catch(IOException e) {
		 
				 }
			}
	 	} 
		 IAdminService service = AdminServiceImpl.getInstance();
		 
		 int cnt = service.updateStudent(stuVo);
		 
		 response.sendRedirect(request.getContextPath() + "/managerStudent.do" );
		
		
	}

	private String extractFileName(Part part) {
		String fileName = "";
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() -1);
			}
		}
		
		return fileName;
	}

}
