package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.omg.IOP.ProfileIdHelper;

import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class proModify
 */
@WebServlet("/Info/proModify.do")
@MultipartConfig
public class proModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public proModify() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uploadPath = "D:/D_other/uploadFiles";
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
		request.setCharacterEncoding("utf-8");
		
		String proId = request.getParameter("pro_id");
		String proAddr = request.getParameter("pro_addr");
		String proTel = request.getParameter("pro_tel");
		String prozip = request.getParameter("pro_zip");
		String proMail = request.getParameter("pro_mail");
		String proOldPoto = request.getParameter("pro_photo");
		
//		System.out.println("NullTest1 :::::proId" + proId);
		
		
		
		
		ProfessorVO provo = new ProfessorVO();
		
		provo.setPro_id(proId);
		provo.setPro_addr(proAddr);
		provo.setPro_tel(proTel);
		provo.setPro_zip(prozip);
		provo.setPro_mail(proMail);
		provo.setPro_poto(proOldPoto);

//		System.out.println("NullTest2 ::::: provo " + provo);
		
		Part part = request.getPart("pro_photo");
		
		if(part != null) {
			String photo = extractFileName(part);
			if(!"".equals(photo)) {
				try {
					String savePhoto = UUID.randomUUID().toString();
					part.write(uploadPath + File.separator + savePhoto);
					provo.setPro_poto(savePhoto);
				} catch (IOException e) {
					// TODO: handle exception
				}
				
			}
		}
		IProfessorService service = ProfessorServiceImpl.getInstance();
 		int cnt = service.updatepro(provo);
		
 		response.sendRedirect( request.getContextPath() + "/Info/proView.do?proId="+ proId);
		
	}


	private String extractFileName(Part part) {
		String fileName = "";
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] items= contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		return fileName;
	}

}
