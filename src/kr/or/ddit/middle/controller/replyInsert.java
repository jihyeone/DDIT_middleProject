package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CB_ComentServiceImpl;
import kr.or.ddit.middle.service.ICB_ComentService;
import kr.or.ddit.middle.vo.Comm_CommentVO;

/**
 * Servlet implementation class replyInsert
 */
@WebServlet("/reply/replyInsert.do")
public class replyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public replyInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String com_no =  request.getParameter("com_no");
		String stu_id =  request.getParameter("stu_id");
		String cmnt_cn =  request.getParameter("cmnt_cn");
		
		ICB_ComentService service = CB_ComentServiceImpl.getInstance();
		Comm_CommentVO vo = new Comm_CommentVO();
		
		
		vo.setComm_no(com_no);
		vo.setStu_id(stu_id);
		vo.setCmnt_cn(cmnt_cn);
		
		int cnt = service.insertReply(vo);
		
		
		
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
		
		
		
	}

}
