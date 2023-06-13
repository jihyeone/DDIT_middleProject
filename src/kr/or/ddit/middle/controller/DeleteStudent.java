package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;

@WebServlet("/deleteStudent.do")
public class DeleteStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String studentId = request.getParameter("stu_id");
		IAdminService service = AdminServiceImpl.getInstance();
		
		int cnt = service.deleteStudent(studentId);
		
	response.sendRedirect( request.getContextPath() + "/managerStudent.do" );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
