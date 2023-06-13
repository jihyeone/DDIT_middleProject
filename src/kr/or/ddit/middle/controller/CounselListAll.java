package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.vo.CounselVO;

/**
 * Servlet implementation class CounselListAll
 */
@WebServlet("/counsel/CounselListAll.do")
public class CounselListAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounselListAll() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	
	ICounselService service = CounselServiceImpl.getInstance();
	
	System.out.println("확인");
	
	List<CounselVO> clistAll = service.getCounselList();
	
	request.setAttribute("clistAll", clistAll);
	
	request.setAttribute("viewPage", "/WEB-INF/view/Counsel/adminCounselList.jsp");
	
	request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
