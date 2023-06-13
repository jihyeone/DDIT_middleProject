package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.RoomVO;

@WebServlet("/RoomSearch.do")
public class RoomSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rm_type = request.getParameter("rm_type");
		IAdminService service = AdminServiceImpl.getInstance();
		List<RoomVO> roomList = service.SearchRoom(rm_type);
		
		request.setAttribute("roomList",roomList);
		request.setAttribute("viewPage","/WEB-INF/view/Info/RoomList.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
