package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.ProfessorVO;

/**
 * Servlet implementation class LBoardListPro
 */
@WebServlet("/lboard/lBoardListPro.do")
public class LBoardListPro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String lbgu = request.getParameter("lb_gu");
		String proId = request.getParameter("pro_id");
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		IProfessorService proService = ProfessorServiceImpl.getInstance();
		
//		List<Lect_BoardVO> list = service.getLBList(proId);
		List<Lect_BoardVO> list = service.joinlboard();
		List<ProfessorVO> prolist = proService.getAllprofesser();
		List<LB_CodeVO> cdlist = service.getAllCode();
		
		request.setAttribute("LBListPro", list);
		request.setAttribute("proList", prolist);
		request.setAttribute("cdList", cdlist);
		
		request.setAttribute("viewPage", "/WEB-INF/view/LBoard/LB_list.jsp");
	
		request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);	
//		request.getRequestDispatcher("/WEB-INF/view/LBoard/LB_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
