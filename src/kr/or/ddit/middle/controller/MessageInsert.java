package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import kr.or.ddit.middle.service.IMessageService;
import kr.or.ddit.middle.service.MessageServiceImpl;
import kr.or.ddit.middle.vo.MessageVO;

@WebServlet("/messageInsert.do")
public class MessageInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String sendId = request.getParameter("reciv_id"); 
		String reciId = request.getParameter("send_id");
		String title = request.getParameter("msg_ttl");
		String cont = request.getParameter("msg_cn");
		cont = cont.replaceAll("\n", "<br>");
		
		MessageVO msgvo = new MessageVO();
		
		System.out.println("보내는사람 = " + sendId);
		System.out.println("받은사람 = " + reciId);
		System.out.println("제목" + title);
		System.out.println("내용 " + cont);
		
		
		
		try {
			BeanUtils.populate(msgvo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		msgvo.setSend_id(sendId);
		msgvo.setReciv_id(reciId);
		msgvo.setMsg_ttl(title);
		msgvo.setMsg_cn(cont);
		
		System.out.println(msgvo);
		
		IMessageService service = MessageServiceImpl.getInstance();
		
		int res = service.insertMessage(msgvo);
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/Message/messageInsertResult.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
