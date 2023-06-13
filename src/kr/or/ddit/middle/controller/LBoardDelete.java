package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;

@WebServlet("/lboard/lBoardDelete.do")
public class LBoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String lbNo = request.getParameter("lboard_no");
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		
		int res = service.deleteLBoard(lbNo);
		int fres = service.deleteFile(lbNo);
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

}
