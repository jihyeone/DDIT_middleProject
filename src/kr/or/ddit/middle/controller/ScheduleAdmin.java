package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sun.org.apache.bcel.internal.generic.ISUB;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.service.IscheudleService;
import kr.or.ddit.middle.service.ScheudleServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ScheduleVO;

/**
 * Servlet implementation class ScheduleAdmin
 */
@WebServlet("/schedule/ScheduleAdmin.do")
public class ScheduleAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	request.setCharacterEncoding("utf-8");
	
	String classNo = request.getParameter("classNo");
	
	IscheudleService scduService = ScheudleServiceImpl.getInstance();
	IClassService classSerivce = ClassServiceImpl.getInstance();
	
	// 학급리스트
	List<ClassClassVO> classList = classSerivce.getAllclass();
	
	// 과목 리스트 불러오기
	List<ScheduleVO> sublist = scduService.getAllScheudle();
	
	JSONArray jsonArr = new JSONArray();
	
	
	List<Map<String, ScheduleVO>> schedulList = null;
	
	if(classNo != null) { // 학급정보가 있으면 그에 해당하는 학급 스케줄 리스트 보내기
		schedulList = (List<Map<String, ScheduleVO>>) scduService.calenAllList(classNo);
	
		for (Map<String, ScheduleVO> map : schedulList) {
			jsonArr.add(convertMapToJson(map));
		}
		System.out.println(classNo + "반의 데이터 = " + jsonArr );
		request.setAttribute("jsonArr", jsonArr);
		
		request.setAttribute("schedulList", schedulList);
		
		
	} else if(classNo == null) {
		ScheduleVO svo = new ScheduleVO();
		svo.setTitle("null");
		Map<String, ScheduleVO> nullschedule = new HashMap<String, ScheduleVO>();
		nullschedule.put("title", svo);
		List<Map<String, ScheduleVO>> nullList = new ArrayList<Map<String,ScheduleVO>>();
		nullList.add(nullschedule);
		request.setAttribute("schedulList", nullList);
		
		request.setAttribute("jsonArr", jsonArr);
	}
	
	request.setAttribute("setclass", classNo);
	request.setAttribute("sublist", sublist);
	request.setAttribute("classList", classList);
	request.setAttribute("viewPage", "/WEB-INF/view/Calender/CalenderAdmin.jsp");
	request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
