package kr.or.ddit.middle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.ProfessorVO;

@WebServlet("/DetailProfessor.do")
public class DetailProfessor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      request.setCharacterEncoding("utf-8");
	      String pro_id = request.getParameter("pro_id");
	      IAdminService service = AdminServiceImpl.getInstance();
	      ProfessorVO professorVO  = service.getProfessor(pro_id);
	      request.setAttribute("professorVo",professorVO);
	      
	      request.setAttribute("viewPage","/WEB-INF/view/MainAdmin/DetailProfessor.jsp" );
	      
	      request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
