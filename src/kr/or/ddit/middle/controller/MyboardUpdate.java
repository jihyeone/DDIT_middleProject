package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.vo.Comm_BoardVO;

/**
 * Servlet implementation class MyboardUpdate
 */
@WebServlet("/myboard/MyboardUpdate.do")
public class MyboardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyboardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String comm_no = request.getParameter("cNo");
		
		String com_cn = request.getParameter("com_cn");
		com_cn = com_cn.replaceAll("\n", "<br>");
		
		String cns_ttl = request.getParameter("com_ttl");
		String selectgu = request.getParameter("selectgu");
		
		Comm_BoardVO cvo = new Comm_BoardVO();
		
		cvo.setComm_ttl(cns_ttl);
		cvo.setComm_cn(com_cn);
		cvo.setComm_gu(selectgu);
		cvo.setComm_no(comm_no);
		
		ICBoardService service = CBoardServiceImpl.getInstance();
		
		int cnt = service.UpdateMyBoard(cvo);
		
		System.out.println("성공값확인 :::" + cnt);
		
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
		
	}

}
