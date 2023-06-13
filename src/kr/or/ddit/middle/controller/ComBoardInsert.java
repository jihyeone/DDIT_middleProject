package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class ComBoardUpdate
 */
@WebServlet("/board/ComBoardInsert.do")
public class ComBoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComBoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String com_ttl = request.getParameter("com_ttl");
		String selectgu = request.getParameter("selectgu");
		String com_cn = request.getParameter("com_cn");
		com_cn = com_cn.replaceAll("\n", "<br>");
		HttpSession session = request.getSession();
		StudentVO stuvo = (StudentVO) session.getAttribute("LoginStudent");
		
		Comm_BoardVO cvo = new Comm_BoardVO();
		
		System.out.println(selectgu);
		
		cvo.setComm_ttl(com_ttl);
		cvo.setStu_id(stuvo.getStu_id());
		cvo.setComm_gu(selectgu);
		cvo.setComm_cn(com_cn);
		
		ICBoardService service = CBoardServiceImpl.getInstance();
		
		int cnt = service.insertComboard(cvo);
		
		
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

}
