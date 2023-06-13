package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.vo.CounselVO;

/**
 * Servlet implementation class CounselList
 */
@WebServlet("/counsel/CounselList.do")
public class CounselList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	
	String stuId = request.getParameter("stuId");
	System.out.println("로그인 아이디는 = " + stuId);
	
	ICounselService service = CounselServiceImpl.getInstance();
	
	List<CounselVO> list = service.selectCounselList(stuId);
	
	request.setAttribute("stucounselList", list);
	
	request.setAttribute("viewPage", "/WEB-INF/view/Counsel/stuCounselList.jsp");
	
	request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
