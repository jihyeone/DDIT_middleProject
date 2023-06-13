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
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.swing.internal.plaf.metal.resources.metal;

import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class StuImageView
 */
@WebServlet("/Info/StuImageView.do")
public class StuImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuImageView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		HttpSession session = request.getSession();
//		StudentVO stdvo = (StudentVO) session.getAttribute("LoginStudent");
		
		String stdId = request.getParameter("stuId");
		
		System.out.println("최근상담메세지 학생 아이디 = " + stdId);
		
		IStudentService service = StudentServiceImpl.getInstance();
//		System.out.println("이미지 프린트");
		StudentVO stdvo = service.getStuInfo(stdId);
		String fileName = stdvo.getStu_poto();
		
		if(fileName==null) {
			fileName = "noImage.jpg";
		}
		String imagePath = "D:/D_other/uploadFiles";
		
		String imageFilePath = imagePath + File.separator + fileName;
		
		File file = new File(imageFilePath);
		
		if(file.exists()) {
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			
			byte[] buffer = new byte[1024];
			int len = -1;
			
			while((len = bis.read(buffer))>0 ) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
