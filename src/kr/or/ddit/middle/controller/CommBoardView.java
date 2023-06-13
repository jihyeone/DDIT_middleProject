package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import kr.or.ddit.middle.service.CB_ComentServiceImpl;
import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICB_ComentService;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;
import kr.or.ddit.middle.vo.Comm_CommentVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class CommBoardView
 */
@WebServlet("/board/CommBoardView.do")
public class CommBoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommBoardView() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cNo = request.getParameter("cNo");
		
		HttpSession session = request.getSession();
		StudentVO svo = (StudentVO) session.getAttribute("LoginStudent");
		ProfessorVO pvo = (ProfessorVO) session.getAttribute("LoginProfessor");
		AdminVO adminvo = (AdminVO) session.getAttribute("LoginAdmin");
		
		ICBoardService service = CBoardServiceImpl.getInstance();
		ICB_ComentService rservice = CB_ComentServiceImpl.getInstance();
		Comm_BoardVO cvo = service.ViewComBoard(cNo);
		List<Comm_CodeVO> codevo =  (List<Comm_CodeVO>)service.getcomcode();
		Comm_CodeVO guvo = (Comm_CodeVO) service.getcomgu(cNo);
		
		List<Comm_CommentVO> rlist =(List<Comm_CommentVO>) rservice.selectReply(cNo);
		
		int rc = Integer.parseInt(cvo.getComm_rc());
		rc++;
		String rc2 = "" + rc;
		cvo.setComm_rc(rc2);
		service.updateRc(cNo);
		
		request.setAttribute("replyList", rlist);
		request.setAttribute("svo", svo);
		request.setAttribute("BoardView", cvo);
		request.setAttribute("codevo", codevo);
		request.setAttribute("gu", guvo);
		
		if(svo != null) {
			request.setAttribute("viewPage", "/WEB-INF/view/CommBoard/ComBoardView.jsp");
			request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
		} else if(pvo != null) {
			request.setAttribute("viewPage", "/WEB-INF/view/CommBoard/ComBoardViewpro.jsp");
			request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
		} else if(adminvo != null) {
			request.setAttribute("viewPage", "/WEB-INF/view/CommBoard/ComBoardViewpro.jsp");
			request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
