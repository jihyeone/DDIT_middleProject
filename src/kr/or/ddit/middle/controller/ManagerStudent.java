package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/managerStudent.do")
public class ManagerStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IStudentService stuService = StudentServiceImpl.getInstance();
		IClassService classService = ClassServiceImpl.getInstance();
		
		
		String classNo = request.getParameter("classNo");
		
		List<StudentVO> list = null;
		
		List<ClassClassVO> classList = classService.getAllclass();
		
		
		
		if( classNo == null ||classNo.equals("allClass")) classNo = null;
		
		
		
		if(classNo != null ) { // 학급 목록이 null이 아니면 실행 또는 classNo가 allcalss가 아니면
			list = stuService.getAllstudent(classNo);
			request.setAttribute("list", list);
			
		} else if(classNo == null){
			list = stuService.getAllstudent();
			request.setAttribute("list", list);
		}
		// classNo
		request.setAttribute("classList", classList);
		request.setAttribute("viewPage", "/WEB-INF/view/Info/InfoStudentAd.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
