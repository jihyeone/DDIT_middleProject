package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.CB_ComentServiceImpl;
import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICB_ComentService;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;
import kr.or.ddit.middle.vo.Comm_CommentVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class CommBoard
 */
@WebServlet("/board/CommBoard.do")
public class CommBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommBoard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		StudentVO stuvo = (StudentVO) session.getAttribute("LoginStudent");
		ProfessorVO provo = (ProfessorVO)session.getAttribute("LoginProfessor");
		AdminVO adminvo = (AdminVO)session.getAttribute("LoginAdmin");
		ICBoardService cservice = CBoardServiceImpl.getInstance();
		
		//--- 페이징
		int spage = Integer.parseInt( request.getParameter("spage"));
		
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		
		System.out.println("써블렛에 넘어온 stype키값=" + stype);
		System.out.println("써블렛에 넘어온 sword값 데이터 =" + sword);
		
		
		PageVO pagevo = cservice.pageInfo(spage, stype, sword);
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", pagevo.getStart());
		map.put("end", pagevo.getEnd());
		map.put("stype", stype);
		map.put("sword", sword);
		
//		List<Comm_BoardVO> list = cservice.ComBoardList(); // 원본리스트
		List<Comm_BoardVO> list  = cservice.CBoardBypage(map);
		
		List<Comm_CodeVO> codevo =  (List<Comm_CodeVO>)cservice.getcomcode();
		
		request.setAttribute("stype", stype);
		request.setAttribute("sword", sword);
		request.setAttribute("pagevo", pagevo);
		request.setAttribute("currentPage", spage);
		request.setAttribute("comBoardList", list);
		request.setAttribute("codevo", codevo);
		
		request.setAttribute("viewPage", "/WEB-INF/view/CommBoard/ComBoardList.jsp");
		
		if(stuvo != null) {
			request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
			
		} else if(provo != null) {
			request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
			
		} else if (adminvo != null) {
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
