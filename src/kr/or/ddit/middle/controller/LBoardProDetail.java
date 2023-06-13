package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

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
import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.ProfessorVO;

@WebServlet("/lboard/lBoardProDetail.do")
public class LBoardProDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
		System.out.println("확인 :: " + Loginpro);
		
		String lbNo = request.getParameter("lboard_no");
		System.out.println("서블렛 === ");
		System.out.println("눌른 글번호 = " + lbNo);
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		IProfessorService pService = ProfessorServiceImpl.getInstance();
		
		Lect_BoardVO lbVO = service.getLBDetail(lbNo);
		LB_CodeVO codeVO = service.joinLBCode(lbNo);
		ProfessorVO proVO = pService.professerone(lbVO.getPro_id());
		LB_AttachVO fileVO = service.getFileLBNo(lbVO.getLboard_no());
		
		List<LB_CodeVO> cdList = service.getAllCode();
		
		int rc = Integer.parseInt(lbVO.getLboard_rc()); 
		rc ++;
		String rc2 = "" + rc;
		lbVO.setLboard_rc(rc2);
		service.updateLBoardRC(lbNo);
		
		session.setAttribute("LoginProfessor", Loginpro);
		request.setAttribute("LBDetail", lbVO);
		request.setAttribute("codeVO", codeVO);
		request.setAttribute("proVO", proVO);
		
		
		if(fileVO!=null) {
			request.setAttribute("fileVO", fileVO);
		}
		request.setAttribute("cdList", cdList);
		
		
		System.out.println("LoginProfessor = " + Loginpro);
		System.out.println("LBDetail = " + lbVO);
		System.out.println("codeVO = " + codeVO);
		System.out.println("codeVO = " + codeVO.getLb_nm());
		System.out.println("proVO = " + proVO);
		System.out.println("cdList = " + cdList);
		
		request.setAttribute("viewPage", "/WEB-INF/view/LBoard/LB_proDetail.jsp");
		request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
