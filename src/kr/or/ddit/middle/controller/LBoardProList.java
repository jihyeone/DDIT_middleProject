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

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.LB_CodeVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;

@WebServlet("/lboard/lBoardProList.do")
public class LBoardProList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String pro_id = request.getParameter("proId");
		System.out.println("로그인아이디 : " + pro_id);
		
		String lfile_no = request.getParameter("lfile_no");
		System.out.println("파일번호 : " + lfile_no);
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		IProfessorService proService = ProfessorServiceImpl.getInstance();
		
		
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
		
		
//		List<Lect_BoardVO> list = service.joinlboard();
		List<LB_CodeVO> codeList = service.getAllCode();
		List<ProfessorVO> proList = proService.getAllprofesser();
		
		LB_AttachVO fileVO = service.joinLBFile(lfile_no);
		
		request.setAttribute("pagevo", pagevo);
		request.setAttribute("currentPage", spage);
		
		request.setAttribute("list", list);
		request.setAttribute("codeList", codeList);
		request.setAttribute("proList", proList);
		request.setAttribute("fileVO", fileVO);
		
		request.setAttribute("viewPage", "/WEB-INF/view/LBoard/LB_proList.jsp");
		request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}