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

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;

/**
 * Servlet implementation class CounselList
 */
@WebServlet("/counsel/CounselListstu.do")
public class CounselListstu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	
	String stuId = request.getParameter("stuId");
	System.out.println("로그인 아이디는 = " + stuId);
	
	int spage = Integer.parseInt( request.getParameter("spage"));
	
	String stype = null;
	String sword = null;
	
	
	ICounselService cnsService = CounselServiceImpl.getInstance();
	IProfessorService proService = ProfessorServiceImpl.getInstance();
	
	PageVO pagevo = cnsService.pageInfoStu(spage, stype, sword, stuId);
	
	Map<String, Object> map = new HashMap<>();
	map.put("start", pagevo.getStart());
	map.put("end", pagevo.getEnd());
	map.put("stype", stype);
	map.put("sword", sword);
	map.put("stu_id", stuId);
	
	
//	List<CounselVO> list = cnsService.selectCounselList(stuId);
	List<CounselVO> list = cnsService.StuCnsBypage(map);
	
	
	List<ProfessorVO> prolist = proService.getAllprofesser();
	
	request.setAttribute("currentPage", spage);
	request.setAttribute("pagevo", pagevo);
	request.setAttribute("stucounselList", list);
	request.setAttribute("proList", prolist);
	
	request.setAttribute("viewPage", "/WEB-INF/view/Counsel/stuCounselList.jsp");
	
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
