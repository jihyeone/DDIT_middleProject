package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/StudentList.do")
public class StudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//값을 받아온다
		String class_no = request.getParameter("class_no");
		//서비스 인스턴스 만들기
		IAdminService service = AdminServiceImpl.getInstance();
		IClassService cservice = ClassServiceImpl.getInstance();
		
		//객체 받아서 저장해주기
		List<StudentVO> stdList = service.getStudent(class_no);
		ClassClassVO classVo = service.getClass(class_no);
		ClassClassVO classVo2 = cservice.SelectJoinClass(class_no);
		
		System.out.println("classVo2"+classVo2);
		
		//데이터값 세팅
		request.setAttribute("classVo", classVo);
		request.setAttribute("classList",classVo2);
		request.setAttribute("studentList", stdList);
		
		request.setAttribute("viewPage","/WEB-INF/view/MainAdmin/StudentList.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
