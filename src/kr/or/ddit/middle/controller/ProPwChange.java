package kr.or.ddit.middle.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class ProPwChange
 */
@WebServlet("/Info/ProPwChange.do")
public class ProPwChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProPwChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getRequestDispatcher("/WEB-INF/view/Info/proPwChange.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		ProfessorVO provo = (ProfessorVO) session.getAttribute("LoginProfessor");
		
		String pw = request.getParameter("pwd");
		String nPw = request.getParameter("new_pwd");
		String proId = provo.getPro_id();
		
		System.out.println("npw: " +  nPw);
		System.out.println("proId : " + proId);
		
		IProfessorService service = ProfessorServiceImpl.getInstance();
		
		ProfessorVO pwReset = new ProfessorVO();
		
		pwReset.setPro_pw(nPw);
		pwReset.setPro_id(proId);
		
				
		int cnt = service.modifyPw(pwReset);
		request.setAttribute("result", cnt);
		
		
		request.getRequestDispatcher("/WEB-INF/view/Info/result.jsp").forward(request, response);
		
	}

}
