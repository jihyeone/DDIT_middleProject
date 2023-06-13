package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.RoomVO;

@WebServlet("/UpdateClass.do")
public class UpdateClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String class_no = request.getParameter("class_no");
		
		IProfessorService proService = ProfessorServiceImpl.getInstance();
		
		List<ProfessorVO> plist = proService.getAllprofesser(); // 교수목록
		
		IAdminService service = AdminServiceImpl.getInstance();
		ClassClassVO ClassVo = service.getClass(class_no);
		
//		System.out.println(class_no);
//		System.out.println(ClassVo);
		
		request.setAttribute("plist", plist);
		request.setAttribute("classvo", ClassVo);
		request.setAttribute("viewPage", "/WEB-INF/view/MainAdmin/UpdateClass.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				request.setCharacterEncoding("utf-8");
				String class_no = request.getParameter("class_no");
				String rm_no    = request.getParameter("rm_no");
				String pro_id   = request.getParameter("pro_id");
				String tr_id    = request.getParameter("tr_id");
				String s_date   = request.getParameter("s_date");
				String e_date   = request.getParameter("e_date");
				String cl_fin = request.getParameter("cl_fin");
				
				ClassClassVO classVo = new ClassClassVO();
				classVo.setClass_no(class_no);
				classVo.setRm_no(rm_no);
				classVo.setPro_id(pro_id);
				classVo.setTr_id(tr_id);
				classVo.setS_date(s_date);
				classVo.setE_date(e_date);
				classVo.setCl_fin(cl_fin);
				System.out.println("학급 = " + class_no);
				System.out.println("수료 여부 = " + cl_fin); 
				
				IAdminService service = AdminServiceImpl.getInstance();
				
				RoomVO rmvo = new RoomVO();
				int cnt = service.updateClass(classVo);
				int res = 0;
				
				if(cl_fin.equals("Y")) {
					// 학급이 수료라면 강의실 변경
					rmvo.setRm_no(rm_no);
					rmvo.setUseyn("N");
					res = service.FinClassRoomUpdate(rmvo);
				} else if(cl_fin.equals("N")) {
					rmvo.setRm_no(rm_no);
					rmvo.setUseyn("Y");
					res = service.FinClassRoomUpdate(rmvo);
				}
				
				
				response.sendRedirect(request.getContextPath()+"/ClassList.do");
		
	}

}
