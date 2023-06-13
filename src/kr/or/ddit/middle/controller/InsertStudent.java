package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/insertStudent.do")
@MultipartConfig
public class InsertStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		IClassService classService = ClassServiceImpl.getInstance();
		List<ClassClassVO> clist = classService.getAllclass();
		
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("/WEB-INF/view/MainAdmin/InsertStudent.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "D:/A_TeachingMaterial/03_HighJava/workspace/dditMiddlePJ/WebContent/images";
		
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		
		request.setCharacterEncoding("utf-8");
		String stuId = request.getParameter("stu_id");
		String stuNm = request.getParameter("stu_nm"); 
		String stuReg = request.getParameter("stu_reg"); 
		String classNo = request.getParameter("class_no");
		String stuAddr = request.getParameter("stu_addr"); 
		String stuMail = request.getParameter("stu_mail"); 
		String stuTel = request.getParameter("stu_tel"); 
		String stuZip = request.getParameter("stu_zip");
		String stuPoto = request.getParameter("stu_poto");
		
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		/*
		 * out.println(stuId); out.println(stuNm); out.println(stuReg);
		 * out.println(classNo); out.println(stuAddr); out.println(stuMail);
		 * out.println(stuTel); out.println(stuZip); out.println(stuPoto);
		 */
		
		StudentVO stuvo = new StudentVO();
		stuvo.setStu_id(stuId);
		stuvo.setStu_nm(stuNm);
		stuvo.setStu_reg(stuReg);
		stuvo.setClass_no(classNo);
		stuvo.setStu_addr(stuAddr);
		stuvo.setStu_mail(stuMail);
		stuvo.setStu_tel(stuTel);
		stuvo.setStu_zip(stuZip);
		stuvo.setStu_poto(stuPoto);
		
		Part part = request.getPart("stu_poto");
		
		if(part!=null) {
			String poto = extractFileName(part);
			if(!"".equals(poto)) {
				try {
					String savepoto = UUID.randomUUID().toString();
					part.write(uploadPath + File.separator + savepoto);
					stuvo.setStu_poto(savepoto);
				} catch (IOException e) {
					stuvo.setStu_poto(null);
				}
			}
		}
		
		IAdminService service = AdminServiceImpl.getInstance();
		
		int cnt = service.insertStudent(stuvo);
		
		out.write("<script>opener.location.reload(); window.close();</script>");
		
	
	}

	private String extractFileName(Part part) {
		String fileName = "";
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		
		return fileName;
	}

}