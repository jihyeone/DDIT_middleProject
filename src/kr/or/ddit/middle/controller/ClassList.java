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
import kr.or.ddit.middle.vo.ClassClassVO;

@WebServlet("/ClassList.do")
public class ClassList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		IClassService service = ClassServiceImpl.getInstance();
		
		List<ClassClassVO> classList = service.getAllclass();
		
		request.setAttribute("classList", classList);
		request.setAttribute("viewPage","/WEB-INF/view/Info/ClassList.jsp");
		
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
