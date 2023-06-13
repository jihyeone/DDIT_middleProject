package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.middle.service.IscheudleService;
import kr.or.ddit.middle.service.ScheudleServiceImpl;
import kr.or.ddit.middle.vo.ScheduleVO;

/**
 * Servlet implementation class ScheduleAdd
 */
@WebServlet("/schedule/ScheduleAdd.do")
public class ScheduleAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	request.setCharacterEncoding("utf-8");
	ScheduleVO vo = new ScheduleVO();
	
	String title = request.getParameter("title");
	String start = request.getParameter("start");
	String end = request.getParameter("end");
	String class_no = request.getParameter("class_no"); 
		
	vo.setTitle(title);
	vo.setStart(start);
	vo.setEnd(end);
	vo.setClass_no(class_no);
	
	
	String ttl = vo.getTitle();
	
	switch (ttl) {
	case "데이터베이스": vo.setBackgroundColor("#98FBE9"); break;
	case "Basic JAVA": vo.setBackgroundColor("#AD99D8"); break;
	case "High JAVA": vo.setBackgroundColor("#A8DDA6"); break;
	case "jQuery": vo.setBackgroundColor("#59D4D6"); break;
	case "WEB APP": vo.setBackgroundColor("#AEB2EF"); break;
	case "Python": vo.setBackgroundColor("#FFE47C"); break;
	case "WEB PRO": vo.setBackgroundColor("#E26872"); break;
	case "PROJECT": vo.setBackgroundColor("#BDCE7D"); break;
	default:
		break;
	}
	vo.setTextColor("#000000"); // 글자색 검정 = 고정값
	
	
	IscheudleService service = ScheudleServiceImpl.getInstance();
	
	int res = service.insertsAddchedule(vo);
	
	System.out.println("성공 스케줄 번호 = " + res);
	
	request.setAttribute("result", res);
	request.getRequestDispatcher("/WEB-INF/view/result/resultSchedule.jsp").forward(request, response);
	}

}
