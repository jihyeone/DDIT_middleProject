package kr.or.ddit.middle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.vo.Lect_BoardVO;

@WebServlet("/lboard/lBoardModify.do")
@MultipartConfig
public class LBoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String lboard_no = request.getParameter("lboard_no");
		String lfile_no = request.getParameter("lfile_no");
		
		System.out.println("글 번호 :: " + lboard_no);
		System.out.println("파일 번호 :: " + lfile_no);
		
		// 게시글 수정
		String lboard_cn = request.getParameter("lboard_cn");
		lboard_cn = lboard_cn.replaceAll("\n", "<br>");
		System.out.println(lboard_cn);
		
		String lboard_ttl = request.getParameter("lboard_ttl");
		System.out.println(lboard_ttl);
		String selectgu = request.getParameter("selectgu");
		
		Lect_BoardVO lbVO = new Lect_BoardVO();
		
		lbVO.setLboard_ttl(lboard_ttl);
		lbVO.setLboard_cn(lboard_cn);
		lbVO.setLb_gu(selectgu);
		lbVO.setLboard_no(lboard_no);
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		
		int res = service.updateLBoard(lbVO);
		System.out.println("성공 확인 : " + res);
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
