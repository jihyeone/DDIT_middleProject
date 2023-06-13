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

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/lboard/lBoardList.do")
public class LBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		StudentVO stuvo = (StudentVO) session.getAttribute("LoginStudent");
		AdminVO adminvo = (AdminVO)session.getAttribute("LoginAdmin");
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		IProfessorService pService = ProfessorServiceImpl.getInstance();
		
//		List<Lect_BoardVO> list = service.joinlboard();
		
		
		//--- 페이징
		int spage = Integer.parseInt( request.getParameter("spage"));
		
		String stype = request.getParameter("stype");
		String sword = request.getParameter("sword");
		
		PageVO pagevo = service.pageInfoLB(spage, stype, sword );
		
		Map<String, Object> map = new HashMap<>();
		map.put("start", pagevo.getStart());
		map.put("end", pagevo.getEnd());
		map.put("stype", stype);
		map.put("sword", sword);
		
		System.out.println("map 정보 = " + map);
		
		List<Lect_BoardVO> list = service.LBListBypage(map);
		
		
		request.setAttribute("pagevo", pagevo);
		request.setAttribute("currentPage", spage);
		
		
		
		
		
		
		
		List<LB_CodeVO> codeList = service.getAllCode();
		List<ProfessorVO> proList = pService.getAllprofesser();
		
		request.setAttribute("list", list);
		request.setAttribute("codeList", codeList);
		request.setAttribute("proList", proList);
		
		request.setAttribute("viewPage", "/WEB-INF/view/LBoard/LB_List.jsp");
		
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
