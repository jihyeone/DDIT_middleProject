package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICBoardService;


@WebServlet("/board/comBoardDelete.do")
public class ComBoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ComBoardDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String comNo = request.getParameter("com_no");
		
		ICBoardService service = CBoardServiceImpl.getInstance();
		
		int res = service.DeleteMyBoard(comNo);
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

}
