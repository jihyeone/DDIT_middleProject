package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.vo.CounselVO;

/**
 * Servlet implementation class CounselDetail
 */
@WebServlet("/counsel/CncDetail.do")
public class CounselDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	
	String cnsNo = request.getParameter("cnsNo");
	
	ICounselService service = CounselServiceImpl.getInstance();
	
	CounselVO vo = service.counselDetail(cnsNo);
	
	request.setAttribute("cnsDetail", vo);
	
	request.getRequestDispatcher("/WEB-INF/view/Counsel/cnsDetail.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
