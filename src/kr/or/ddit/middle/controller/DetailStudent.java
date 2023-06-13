package kr.or.ddit.middle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/detailStudent.do")
public class DetailStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		ProfessorVO LoginProfessor = (ProfessorVO)session.getAttribute("LoginProfessor");
		AdminVO LoginAdmin = (AdminVO)session.getAttribute("LoginAdmin");
		
		
		String stu_id = request.getParameter("stu_id");
		
		IAdminService service = AdminServiceImpl.getInstance();
		StudentVO StudentVO = service.getStudentAd(stu_id);
		request.setAttribute("studentVo", StudentVO);
	    
	    
		request.setAttribute("viewPage","/WEB-INF/view/MainAdmin/DetailStudent.jsp" );

		if(LoginProfessor != null) {
	    	request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
	    }else if(LoginAdmin != null){
	    	request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	    	
	    }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
