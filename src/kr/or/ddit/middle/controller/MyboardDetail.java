package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

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
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CodeVO;
import kr.or.ddit.middle.vo.Comm_CommentVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class MyboardDetail
 */
@WebServlet("/myboard/MyboardDetail.do")
public class MyboardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyboardDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cNo = request.getParameter("cNo");
		
		HttpSession session = request.getSession();
		StudentVO svo = (StudentVO) session.getAttribute("LoginStudent");
		
		
		ICBoardService service = CBoardServiceImpl.getInstance();
		ICB_ComentService rservice = CB_ComentServiceImpl.getInstance();
		
		Comm_BoardVO cvo = service.ViewMyBoard(cNo);
		List<Comm_CodeVO> codevo =  (List<Comm_CodeVO>)service.getcomcode();
		Comm_CodeVO guvo = (Comm_CodeVO) service.getcomgu(cNo);
		
		List<Comm_CommentVO> rlist =(List<Comm_CommentVO>) rservice.selectReply(cNo);
		
		request.setAttribute("replyList", rlist);
		request.setAttribute("svo", svo);
		request.setAttribute("MyBoardDetail", cvo);
		request.setAttribute("codevo", codevo);
		request.setAttribute("gu", guvo);
		
		request.setAttribute("viewPage", "/WEB-INF/view/CommBoard/MyBoardDetail.jsp");
		
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
