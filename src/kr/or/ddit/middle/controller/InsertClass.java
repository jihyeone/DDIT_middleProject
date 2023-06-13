package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.RoomVO;
import kr.or.ddit.middle.vo.TrainingVO;

@WebServlet("/InsertClass.do")
public class InsertClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		IClassService service = ClassServiceImpl.getInstance();
		List<ClassClassVO> classList = service.getAllclass();
		
		IProfessorService pservice = ProfessorServiceImpl.getInstance();
		List<ProfessorVO> professorList = pservice.getAllprofesser();
		request.setAttribute("professorList", professorList);
		
		IAdminService aservice = AdminServiceImpl.getInstance();
		List<RoomVO> roomList = aservice.selectlecture();
		request.setAttribute("roomList", roomList);
		System.out.println(roomList);
		
		
		
		List<TrainingVO> trList = aservice.trainingAll();
		request.setAttribute("trList", trList);
		
		request.setAttribute("classList", classList);
		request.setAttribute("viewPage","/WEB-INF/view/MainAdmin/InsertClass.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String class_no = request.getParameter("class_no");
		String rm_no   = request.getParameter("rm_no");
		String pro_id  = request.getParameter("pro_id");
		String tr_id   = request.getParameter("tr_id");
		String s_date  = request.getParameter("s_date");
		String e_date  = request.getParameter("e_date");
		
		response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    ClassClassVO classVo = new ClassClassVO();
	    classVo.setClass_no(class_no);
	    classVo.setRm_no(rm_no);
	    classVo.setPro_id(pro_id);
	    classVo.setTr_id(tr_id);
	    classVo.setS_date(s_date);
	    classVo.setE_date(e_date);
		
	    IAdminService service = AdminServiceImpl.getInstance();
	    
	    int cnt = service.insertClass(classVo);
	    
	    response.sendRedirect(request.getContextPath()+"/ClassList.do");
		
	}

}
