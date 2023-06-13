package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.dao.IStudentDao;
import kr.or.ddit.middle.dao.StudentDaoImpl;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class FindPw
 */
@WebServlet("/find/findPw.do")
public class FindPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/Login/findPw.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String reg = request.getParameter("reg");
		String id = request.getParameter("id");
		
		IStudentService stuService = StudentServiceImpl.getInstance();
		IProfessorService proService = ProfessorServiceImpl.getInstance();
		
		StudentVO svo = new StudentVO();
		ProfessorVO pvo = new ProfessorVO();
		
		svo.setStu_id(id);
		svo.setStu_nm(name);
		svo.setStu_reg(reg);
		
		pvo.setPro_id(id);;
		pvo.setPro_nm(name);;
		pvo.setPro_reg(reg);
		
		System.out.println("입력1:"+svo);
		System.out.println("입력1:"+pvo);
		
		String snpass = "";
		String pnpass = "";
		
		snpass = stuService.subreg(svo);
		pnpass = proService.subreg(pvo);
			//
			System.out.println("입력2:"+snpass);
			System.out.println("입력2:"+pnpass);
			
			StudentVO svo2 = new StudentVO();
			ProfessorVO pvo2 = new ProfessorVO();
			
			if(pnpass != null && snpass == null) {
				
				pvo2.setPro_pw(pnpass);;
				pvo2.setPro_id(id);
				
				int cnt = proService.resetpw(pvo2);
				System.out.println("====입력3:" + cnt);
				
				request.setAttribute("result", cnt);
				
			}else {
				svo2.setStu_pw(snpass);
				svo2.setStu_id(id);
				
				int cnt = stuService.resetpw(svo2);
				System.out.println("입력3:" + cnt);
				
				request.setAttribute("result", cnt);
				
		}
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/FindIdPw/findPwResult.jsp").forward(request, response);
	  

	}

}
