package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.IMessageService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.MessageServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.MessageVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/message/messageList.do")
public class MessageList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("stuId");
		
		IMessageService service = MessageServiceImpl.getInstance();
		IStudentService stdservice = StudentServiceImpl.getInstance();
		
		List<StudentVO> stdlist = stdservice.getAllstudent(); // 학생 목록
		
		
		List<MessageVO> sendlist = service.sendMessageList(id);	// 보낸 쪽지 목록
		List<MessageVO> recilist = service.receiveMessageList(id);	// 받은 쪽지 목록
		
		request.setAttribute("sendlist", sendlist);	// 보낸 쪽지 목록
		request.setAttribute("recilist", recilist);	// 받은 쪽지 목록
		request.setAttribute("stdList", stdlist);	// 학생 목록
		
		request.setAttribute("viewPage", "/WEB-INF/view/Message/messageList.jsp");
		request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
		
//		request.getRequestDispatcher("/WEB-INF/view/Message/messageList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
