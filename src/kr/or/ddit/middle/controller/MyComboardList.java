package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.dao.CBoardDaoImpl;
import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;

/**
 * Servlet implementation class MyComboardList
 */
@WebServlet("/board/MyComboardList.do")
public class MyComboardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyComboardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String stuId = request.getParameter("stuId");
		
		System.out.println("로그인아이디 : " + stuId);
		
		ICBoardService service = CBoardServiceImpl.getInstance();
		
		
		List<Comm_BoardVO> list = service.AllMyBoard(stuId);
		List<Comm_CodeVO> codeList = service.getcomcode();
		
		
		System.out.println("list : " + list);
		System.out.println("코드리스트 :"  +codeList);
		
		
		request.setAttribute("AllMyBoard", list);
		request.setAttribute("codeList", codeList);
		
		
		request.setAttribute("viewPage", "/WEB-INF/view/CommBoard/stuMyBoardList.jsp");
		
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
