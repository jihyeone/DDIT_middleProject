package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.CB_ComentServiceImpl;
import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.ICB_ComentService;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.Comm_CommentVO;

/**
 * Servlet implementation class replyUpdate
 */
@MultipartConfig
@WebServlet("/reply/replyUpdate.do")
public class replyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public replyUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }
   
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String re_no = request.getParameter("up_re_num");
		String re_cont = request.getParameter("com_reply_update");
		re_cont = re_cont.replaceAll("\n", "<br>");
		
		Comm_CommentVO cvo = new Comm_CommentVO();
		
		
		cvo.setCmnt_cn(re_cont);
		cvo.setCmnt_no(re_no);
		
		
		ICB_ComentService service = CB_ComentServiceImpl.getInstance();
		
		System.out.println("나오나요??");
		int cnt = service.modifyReply(cvo);
		
		System.out.println("성공값확인 :::" + cnt);
		
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	
	
	
	
	}

	

	
	
}
