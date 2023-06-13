package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class FindId
 */
@WebServlet("/find/findId.do")
public class FindId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/Login/findId.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	//
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String reg = request.getParameter("reg");

	IStudentService stuService = StudentServiceImpl.getInstance();
	IProfessorService proService = ProfessorServiceImpl.getInstance();
	
	StudentVO svo = new StudentVO();
	ProfessorVO pro = new ProfessorVO();
	
	svo.setStu_nm(name);
	svo.setStu_reg(reg);
	pro.setPro_nm(name);
	pro.setPro_reg(reg);
	
	StudentVO svo2 = stuService.findId(svo);
	ProfessorVO pvo2 = proService.findId(pro);
	
	System.out.println(svo2);
	System.out.println(pvo2);
	
	
	if(svo2!=null) {
		request.setAttribute("result", svo2.getStu_id());
	}else if(pvo2 != null) {
		request.setAttribute("result", pvo2.getPro_id());
	}
	request.getRequestDispatcher("/WEB-INF/view/FindIdPw/findIdResult.jsp").forward(request, response);
	
	}

}
