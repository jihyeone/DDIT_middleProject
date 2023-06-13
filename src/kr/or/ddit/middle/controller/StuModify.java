package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class StuModify
 */
@WebServlet("/Info/stuModify.do")
@MultipartConfig
public class StuModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuModify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		StudentVO stuvo = (StudentVO) session.getAttribute("LoginStudent");
		
		String uploadPath = "D:/D_other/uploadFiles";
		File fileUploadDir = new File(uploadPath);
		if(!fileUploadDir.exists()) {
			fileUploadDir.mkdirs();
		}
	
		request.setCharacterEncoding("utf-8");
		
		String stuId = request.getParameter("stu_id");
		String stuAs = request.getParameter("stu_as");
		String stuAddr = request.getParameter("stu_addr");
		String stuTel = request.getParameter("stu_tel");
		String stuzip = request.getParameter("stu_zip");
		String stuMail = request.getParameter("stu_mail");
		String stuOldPoto = request.getParameter("stu_photo");
		
		StudentVO stdvo = new StudentVO();
		
		stdvo.setStu_id(stuId);
//		stdvo.setStu_as(stuAs);
		stdvo.setStu_addr(stuAddr);
		stdvo.setStu_tel(stuTel);
		stdvo.setStu_zip(stuzip);
		stdvo.setStu_mail(stuMail);
		stdvo.setStu_poto(stuOldPoto);

		
		Part part = request.getPart("stu_photo");
		
		if(part != null) {
			String photo = extractFileName(part);
			if(!"".equals(photo)) {
				try {
					String savePhoto = UUID.randomUUID().toString();
					part.write(uploadPath + File.separator + savePhoto);
					stdvo.setStu_poto(savePhoto);
				} catch (IOException e) {
					// TODO: handle exception
				}
				
			}
		}
 		IStudentService service = StudentServiceImpl.getInstance();
 		int cnt = service.updateStudent(stdvo);
		
 		response.sendRedirect( request.getContextPath() + "/Info/StuInfoList.do?stuId="+ stuId);
		
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
