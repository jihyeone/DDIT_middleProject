package kr.or.ddit.middle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;

import kr.or.ddit.middle.dao.IMessageDao;
import kr.or.ddit.middle.service.IMessageService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.MessageServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.MessageVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/messageSenView.do")
public class MessageSenView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int msgno = Integer.parseInt(request.getParameter("msg_no"));
		
		IMessageService service = MessageServiceImpl.getInstance();
		
		// int res = service.checkMessage(msgno);
		
		MessageVO msgvo = (MessageVO)service.selectMessageNo(msgno);
		
		request.setAttribute("MessageVO", msgvo);
		// request.setAttribute("res", res); 
//		request.getRequestDispatcher("/WEB-INF/view/Message/messageSenView.jsp").forward(request, response);
		
		IStudentService recivStuService = StudentServiceImpl.getInstance();
		StudentVO recivStuvo = recivStuService.getone(msgvo.getReciv_id()) ;
		
		request.setAttribute("reciVO", recivStuvo);
		
		request.setAttribute("viewPage", "/WEB-INF/view/Message/messageSenView.jsp");
		request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
