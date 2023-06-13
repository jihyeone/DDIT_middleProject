package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class StdPwChange2
 */
@WebServlet("/Info/StdPwChange.do")
public class StdPwChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/view/Info/stdPwChange.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		StudentVO stdvo = (StudentVO) session.getAttribute("LoginStudent");
		
		String pw = request.getParameter("pwd");
		String nPw = request.getParameter("new_pwd");
		String stuId = stdvo.getStu_id();
		
		System.out.println("npw: " +  nPw);
		System.out.println("stuId : " + stuId);
		
		IStudentService service = StudentServiceImpl.getInstance();
		
		StudentVO pwReset = new StudentVO();
		
		pwReset.setStu_pw(nPw);
		pwReset.setStu_id(stuId);
				
		int cnt = service.modifyPw(pwReset);
		request.setAttribute("result", cnt);
		
		
		request.getRequestDispatcher("/WEB-INF/view/Info/result.jsp").forward(request, response);
		
	}
	

}
