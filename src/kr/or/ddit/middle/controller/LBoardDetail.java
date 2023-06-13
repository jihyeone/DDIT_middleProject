package kr.or.ddit.middle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/lboard/lBoardDetail.do")
public class LBoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		StudentVO stuvo = (StudentVO) session.getAttribute("LoginStudent");
		AdminVO adminvo = (AdminVO)session.getAttribute("LoginAdmin");
		
		String lbNo = request.getParameter("lboard_no");
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		IProfessorService pService = ProfessorServiceImpl.getInstance();
		
		Lect_BoardVO lbVO = service.getLBDetail(lbNo);
		LB_CodeVO codeVO = service.joinLBCode(lbNo);
		ProfessorVO proVO = pService.professerone(lbVO.getPro_id());
		LB_AttachVO fileVO = service.getFileLBNo(lbVO.getLboard_no());
		
		int rc = Integer.parseInt(lbVO.getLboard_rc()); 
		rc ++;
		String rc2 = "" + rc;
		lbVO.setLboard_rc(rc2);
		service.updateLBoardRC(lbNo);
		
		request.setAttribute("lbVO", lbVO);
		request.setAttribute("codeVO", codeVO);
		request.setAttribute("proVO", proVO);
		request.setAttribute("fileVO", fileVO);
		
		request.setAttribute("viewPage", "/WEB-INF/view/LBoard/LB_Detail.jsp");
		
		if(stuvo != null) {
			request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
