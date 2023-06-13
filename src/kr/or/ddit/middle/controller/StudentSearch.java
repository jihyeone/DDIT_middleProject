package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/studentSearch.do")
public class StudentSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String searchId = request.getParameter("search");
		
		IStudentService service = StudentServiceImpl.getInstance();
		
		List<StudentVO> list = service.ReceiveSelect(searchId);
		
		Gson gson  = new Gson();
		String result = gson.toJson(list);
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
		response.flushBuffer();
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
