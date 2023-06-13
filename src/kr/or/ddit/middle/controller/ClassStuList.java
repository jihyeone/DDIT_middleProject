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
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;

@WebServlet("/classStuList.do")
public class ClassStuList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String classno = request.getParameter("class_no");
		
		IClassService service = ClassServiceImpl.getInstance();
		IProfessorService proservice = ProfessorServiceImpl.getInstance();
		
		List<ClassClassVO> stuList = proservice.selectClassStuList(classno);	// 학생 목록
		
		
		
		request.setAttribute("classStuList", stuList);	// 학생 목록
		
		
		request.setAttribute("viewPage", "/WEB-INF/view/Info/ClassStuList.jsp");
		request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
