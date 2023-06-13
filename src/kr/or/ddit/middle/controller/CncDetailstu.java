package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.ProfessorVO;

/**
 * Servlet implementation class CounselDetail
 */
@WebServlet("/counsel/CncDetailstu.do")
public class CncDetailstu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	
	String cnsNo = request.getParameter("cnsNo");// 상담번호
	
	ICounselService Cnsservice = CounselServiceImpl.getInstance();
	
	CounselVO cvo = Cnsservice.counselDetail(cnsNo);
	
	String proID = cvo.getPro_id();
	
	IProfessorService proservice = ProfessorServiceImpl.getInstance();
	
	
	ProfessorVO pvo = proservice.professerone(proID);
	
	request.setAttribute("cnsDetail", cvo);
	request.setAttribute("provo", pvo);
	
	request.setAttribute("viewPage", "/WEB-INF/view/Counsel/cnsDetailstu.jsp");
	
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
