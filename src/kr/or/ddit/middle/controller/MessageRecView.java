package kr.or.ddit.middle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;

import com.sun.org.apache.bcel.internal.generic.ISUB;

import kr.or.ddit.middle.dao.IMessageDao;
import kr.or.ddit.middle.service.IMessageService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.MessageServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.MessageVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/messageRecView.do")
public class MessageRecView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내가 받은 쪽지함. = reciveid = 나
		// 보낸사람은 send id
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		StudentVO LoginStudent = (StudentVO) session.getAttribute("LoginStudent");
		int msgno = Integer.parseInt(request.getParameter("msg_no"));
		
		IMessageService service = MessageServiceImpl.getInstance();
		
		int res = service.checkMessage(msgno); //읽음확인 update
		
		MessageVO msgvo = (MessageVO)service.selectMessageNo(msgno);
		
		request.setAttribute("MessageVO", msgvo); 
		request.setAttribute("res", res); 
		
		IStudentService sendstuService = StudentServiceImpl.getInstance();
		
		// 나에게 쪽지보낸사람 정보 가져오기
		StudentVO sendstuvo = sendstuService.getone(msgvo.getSend_id()); 
		request.setAttribute("sendVO",sendstuvo);
		
		// 받은 메세지 디테일 볼때 메세지 카운트 다시 세서 세션 학생에 set으로 넣어주기
		int msgCount = service.countNewMsg(LoginStudent.getStu_id());
		LoginStudent.setStu_NewmsgCnt(msgCount);
		
		
		session.setAttribute("LoginStudent", LoginStudent);
		request.setAttribute("viewPage", "/WEB-INF/view/Message/messageRecView.jsp");
		request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
		
//		request.getRequestDispatcher("/WEB-INF/view/Message/messageRecView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
