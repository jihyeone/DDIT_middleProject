package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;

/**
 * Servlet implementation class ManagerProfessor
 */
@WebServlet("/ManagerProfessor.do")
public class ManagerProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		IProfessorService service = ProfessorServiceImpl.getInstance();
		
		List<ProfessorVO> proList = service.getAllprofesser();
		
		request.setAttribute("proList", proList);
		
		request.setAttribute("viewPage","/WEB-INF/view/Info/InfoProfessor.jsp");
		
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
