package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.StudentVO;
import oracle.net.aso.l;


@WebServlet("/Info/StuInfoList.do")
public class StuInfoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StuInfoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String stuId = request.getParameter("stuId");
		
		IStudentService service = StudentServiceImpl.getInstance();
		StudentVO stdVo = service.getStuInfo(stuId);
		
		request.setAttribute("stdVo", stdVo);
		
		
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/Info/InfoStudent.jsp");
//		rd.forward(request, response);
		
		
		request.setAttribute("viewPage", "/WEB-INF/view/Info/InfoStudent.jsp");
		request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
