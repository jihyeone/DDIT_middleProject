package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CB_ComentServiceImpl;
import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICB_ComentService;
import kr.or.ddit.middle.service.ICBoardService;

/**
 * Servlet implementation class replyDelete
 */
@WebServlet("/reply/replyDelete.do")
public class replyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public replyDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String renum = request.getParameter("re_no");
		
		
		ICB_ComentService service = CB_ComentServiceImpl.getInstance();
		
		int cnt = service.deleteReply(renum);
		
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

}
