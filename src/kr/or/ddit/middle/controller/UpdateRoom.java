package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.RoomVO;

@WebServlet("/UpdateRoom.do")
public class UpdateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rmNo = request.getParameter("rm_no");

		IAdminService service = AdminServiceImpl.getInstance();
		RoomVO roomVO = service.selectRoom(rmNo);
		request.setAttribute("roomvo", roomVO);
		request.setAttribute("viewPage","/WEB-INF/view/MainAdmin/RoomList.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rm_no =request.getParameter("rm_no");
		String rm_type = request.getParameter("rm_type");
		String useyn = request.getParameter("useyn");
		
		System.out.println("rm_type의 값??"+rm_type);
		System.out.println("useyn의 값??"+useyn);
		
		RoomVO roomVO = new RoomVO();
		roomVO.setRm_no(rm_no);
		roomVO.setRm_type(rm_type);
		roomVO.setUseyn(useyn);
		
		IAdminService service = AdminServiceImpl.getInstance();
		int cnt = service.updateRoom(roomVO);
		request.setAttribute("result", cnt);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

}
