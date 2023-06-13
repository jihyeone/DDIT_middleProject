package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.SubjectVO;

@WebServlet("/InsertProfessor.do")
@MultipartConfig
public class InsertProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/MainAdmin/InsertPofessor.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 업로드한 파일 서버쪽 폴더 경로 설정
		String uploadPath = "D:/A_TeachingMaterial/03_HighJava/workspace/dditMiddlePJ/WebContent/images";
		
		//저장될 폴더가 없으면 생성
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		request.setCharacterEncoding("utf-8");
		String proId = request.getParameter("pro_id");
		String proNm = request.getParameter("pro_nm");
		String proReg = request.getParameter("pro_reg"); 
//		String proPw = request.getParameter("pro_pw");
		String proAddr = request.getParameter("pro_addr");
		String proMail = request.getParameter("pro_mail");
		String proTel = request.getParameter("pro_tel");
		String proZip = request.getParameter("sub_cd");
		String proCd =request.getParameter("sub_cd");
		
		
		response.setContentType("text/html; charset=UTF-8");
//		
	    PrintWriter out = response.getWriter();
//	    
//	    out.print("아이디"+proId+"<br>");
//	    out.print("이름"+proNm+"<br>");
//	    out.print("주민번호"+proReg+"<br>");
//	    out.print("비밀번호"+proPw+"<br>");
//	    out.print("주소"+proAddr+"<br>");
//	    out.print("이메일"+proMail+"<br>");
//	    out.print("전화번호"+proTel+"<br>");
//	    out.print("우편번호"+proZip+"<br>");
//	    out.print("과목코드"+proCd+"<br>");
		
		ProfessorVO proVo = new ProfessorVO();
		proVo.setPro_id(proId);
		proVo.setPro_nm(proNm);
		proVo.setPro_reg(proReg);
		proVo.setPro_pw(proReg.substring(0,6));
		proVo.setPro_addr(proAddr);
		proVo.setPro_mail(proMail);
		proVo.setPro_tel(proTel);
		proVo.setPro_zip(proZip);
		proVo.setSub_cd(proCd);
		
		Part part = request.getPart("pro_poto");
		
		if(part!=null) {
			String poto  = extractFileName(part);
			if(!"".equals(poto)) {
				try {
					String savePoto = UUID.randomUUID().toString();
					part.write(uploadPath + File.separator +savePoto);
					proVo.setPro_poto(savePoto);
				} catch (IOException e) {
					proVo.setPro_poto(null);
				}
			}
		}
		
		IAdminService service = AdminServiceImpl.getInstance();
		
		int cnt = service.insertProfessor(proVo);
		
		
//		response.sendRedirect(request.getContextPath()+"/ManagerProfessor.do");
		out.write("<script>opener.location.reload(); window.close();</script>");
		
		
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
//