package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.IscheudleService;
import kr.or.ddit.middle.service.ScheudleServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ScheduleVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class ScheduleList
 */
@WebServlet("/schedule/ScheduleList.do")
public class ScheduleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	String stuId = request.getParameter("stuId");
	
	IStudentService stuService = StudentServiceImpl.getInstance();
	StudentVO svo = stuService.getone(stuId);
	String classNo = svo.getClass_no();
	
	IscheudleService schService = ScheudleServiceImpl.getInstance();
	
	List<Map<String, ScheduleVO>> maplist = schService.calenAllList(classNo);
	System.out.println("maplist이 가진 값 = " + maplist);
	
	JSONArray jsonArr = new JSONArray();
	
	for (Map<String, ScheduleVO> map : maplist) {
		jsonArr.add(convertMapToJson(map));
	}
	
	
	
	System.out.println("jsonArr이 가진 값 = " + jsonArr);
	
	
	request.setAttribute("jsonArr", jsonArr); //데이터 받음
	request.setAttribute("maplist", maplist); //데이터 받음
	request.setAttribute("svo", svo); //데이터 받음
	
	
	request.setAttribute("viewPage", "/WEB-INF/view/Calender/Calenderstu.jsp");
	request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
	
	}
	
	public static JSONObject convertMapToJson(Map<String,ScheduleVO> map){
		
		JSONObject json = new JSONObject();
		for(Map.Entry<String, ScheduleVO>entry:map.entrySet()){
			String key = entry.getKey();
			Object value = entry.getValue();
			json.put(key, value);
		}
		return json;
	}
	
}
