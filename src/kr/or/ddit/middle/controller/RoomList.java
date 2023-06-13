package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.vo.PageVO;
import kr.or.ddit.middle.vo.RoomVO;

@WebServlet("/RoomList.do")
public class RoomList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	IAdminService service = AdminServiceImpl.getInstance();
	
	int spage = Integer.parseInt( request.getParameter("spage"));
	
	String rmtype = request.getParameter("rm_type");
	
	System.out.println("룸 타입 = " + rmtype);
	
	PageVO pagevo = service.pageInfoRm(spage,rmtype);
	
	Map<String, Object> map = new HashMap<>();
	map.put("start", pagevo.getStart());
	map.put("end", pagevo.getEnd());
	map.put("rm_type", rmtype);
	
//	List<RoomVO> roomList =service.getRoom();
	List<RoomVO> roomList =service.roomListBypage(map);
	request.setAttribute("pagevo", pagevo);
	request.setAttribute("currentPage", spage);
	
	request.setAttribute("rm_type", rmtype);
	request.setAttribute("roomList", roomList);
	request.setAttribute("viewPage", "/WEB-INF/view/Info/RoomList.jsp");
	request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
