package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;

@WebServlet("/classListPro.do")
public class ClassListPro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String proId = request.getParameter("proId"); // 교수 아이디
		
		IProfessorService proservice = ProfessorServiceImpl.getInstance(); 
		
		response.setContentType("text/html; charset=utf-8");
		
		List<ClassClassVO> clslist = proservice.selectClass(proId); // 교수 아이디와 일치하는 학급 목록 
		
		
		request.setAttribute("classList", clslist); // 학급 목록

		request.setAttribute("viewPage", "/WEB-INF/view/Info/ClassListPro.jsp");
		request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
