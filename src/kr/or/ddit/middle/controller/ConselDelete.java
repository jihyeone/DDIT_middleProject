package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;

/**
 * Servlet implementation class ConselDelete
 */
@WebServlet("/counsel/ConselDelete.do")
public class ConselDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConselDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	
	String cnsNo =  request.getParameter("cns_no");
	
	ICounselService service = CounselServiceImpl.getInstance();
	
	int res = service.deleteCounsel(cnsNo);
	
	System.out.println("성공은 ?? " + res);
	
	request.setAttribute("result", res);
	request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	
	}

}
