package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class CncDetailpro
 */
@WebServlet("/counsel/CncDetailpro.do")
public class CncDetailpro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CncDetailpro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	String cnsNo = request.getParameter("cnsNo");// 상담번호
	
	ICounselService cnsService = CounselServiceImpl.getInstance();
	IStudentService stuService = StudentServiceImpl.getInstance();
	
	CounselVO cvo = cnsService.counselDetail(cnsNo);
	
	String stuId = cvo.getStu_id();
	
	StudentVO svo = stuService.getone(stuId);
	
	request.setAttribute("cnsDetail", cvo);
	request.setAttribute("svo", svo);
	
	request.setAttribute("viewPage", "/WEB-INF/view/Counsel/cnsDetailpro.jsp");
	
	request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
	
	}

}
