package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.IscheudleService;
import kr.or.ddit.middle.service.ScheudleServiceImpl;

/**
 * Servlet implementation class Scheduledelete
 */
@WebServlet("/schedule/Scheduledelete.do")
public class Scheduledelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String scheduleNO = request.getParameter("scheduleNO");
		
		IscheudleService service = ScheudleServiceImpl.getInstance();
		
		int res = service.deleteSchedule(scheduleNO);
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
		
	}

}
