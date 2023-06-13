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
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class CounselListPro
 */
@WebServlet("/counsel/CounselListPro.do")
public class CounselListPro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounselListPro() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	request.setCharacterEncoding("utf-8");
	String proId = request.getParameter("proId");
	int spage = Integer.parseInt( request.getParameter("spage"));
	
	System.out.println("페이지는 = " + spage);
	
	ICounselService cnsService = CounselServiceImpl.getInstance();
	IProfessorService proService = ProfessorServiceImpl.getInstance();
	
	//-------------------------------------------------------
	String stype = null;
	String sword = null;
	
	// 페이지
	PageVO pagevo = cnsService.pageInfo(spage, stype, sword, proId);
	
	Map<String, Object> map = new HashMap<>();
	map.put("start", pagevo.getStart());
	map.put("end", pagevo.getEnd());
	map.put("stype", stype);
	map.put("sword", sword);
	map.put("pro_id", proId);
	
	List<CounselVO> clist = cnsService.CnsListbypage(map);
	
	// 교수가 받은 상담 내용 가져오기
//	List<CounselVO> clist = cnsService.getProCnsList(proId);
	
	// 교수의 정보 불러오기
	ProfessorVO pvo = proService.professerone(proId);
	
	request.setAttribute("currentPage", spage);
	request.setAttribute("clist", clist);
	request.setAttribute("pagevo", pagevo);
	request.setAttribute("pvo", pvo);
	
	request.setAttribute("viewPage", "/WEB-INF/view/Counsel/proCounselList.jsp");
	
	request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
	
	}

}
