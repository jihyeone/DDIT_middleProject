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
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;

@WebServlet("/UpdateProfessor.do")
@MultipartConfig
public class UpdateProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String pro_id = request.getParameter("pro_id");
		
		IAdminService service = AdminServiceImpl.getInstance();
		ProfessorVO proVo = service.getProfessor(pro_id);
		request.setAttribute("proVo",proVo);
		request.setAttribute("viewPage", "/WEB-INF/view/MainAdmin/ProfessorUpdateForm.jsp" );
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadPath = "D:/A_TeachingMaterial/03_HighJava/workspace/dditMiddlePJ/WebContent/images";
		
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		request.setCharacterEncoding("utf-8");
		String pro_id = request.getParameter("pro_id");
		String pro_nm = request.getParameter("pro_nm");
		String pro_reg = request.getParameter("pro_reg");
		String pro_pw =request.getParameter("pro_pw");
		String pro_addr = request.getParameter("pro_addr");
		String pro_mail =request.getParameter("pro_mail");
		String pro_tel = request.getParameter("pro_tel");
		String sub_cd = request.getParameter("sub_cd");
		String pro_zip = request.getParameter("pro_zip");
		String old_poto = request.getParameter("pro_poto");
		
		ProfessorVO proVo = new ProfessorVO();
		proVo.setPro_id(pro_id);
		proVo.setPro_nm(pro_nm);
		proVo.setPro_reg(pro_reg);
		proVo.setPro_pw(pro_pw);
		proVo.setPro_addr(pro_addr);
		proVo.setPro_mail(pro_mail);
		proVo.setPro_tel(pro_tel);
		proVo.setSub_cd(sub_cd);
		proVo.setPro_zip(pro_zip);
		proVo.setPro_poto(old_poto);
		
		Part part = request.getPart("pro_poto");
		
		if(part !=null) {
			String poto = extractFileName(part);
			if(!"".equals(poto)) {
				try {
				String savePoto = UUID.randomUUID().toString();
				part.write(uploadPath+File.separator+savePoto);
				proVo.setPro_poto(savePoto);
				}catch (IOException e) {
				
				}
			}
		}
		IAdminService service = AdminServiceImpl.getInstance();
		
		int cnt = service.updateProfessor(proVo);
		response.sendRedirect(request.getContextPath()+"/ManagerProfessor.do");
		
	}

	private String extractFileName(Part part) {
		String fileName = ""; 
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] items = contentDisposition.split(";");
		for (String item : items) {
			if (item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}
	
}
