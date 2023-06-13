package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;

import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.ClassServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.IClassService;
import kr.or.ddit.middle.vo.ClassClassVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/Search.do")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//값을 받아온다.
		String class_no = request.getParameter("class_no");
		String stu_nm = request.getParameter("ival");
		
		System.out.println("class_no의 값"+class_no);
		System.out.println("stu_nm의 값"+stu_nm);
		
		//서비스 인스턴스 만들기
		IAdminService service = AdminServiceImpl.getInstance();
		IClassService cService = ClassServiceImpl.getInstance();
		
		ClassClassVO classVo = service.getClass(class_no);
		ClassClassVO classVo2 = cService.SelectJoinClass(class_no);
		
		StudentVO stdVO = new StudentVO();
		stdVO.setClass_no(class_no);
		stdVO.setStu_nm(stu_nm);
		
		List<StudentVO> SearchList = service.Search(stdVO);
		
		System.out.println("classVo"+classVo);
		System.out.println("classVo2"+classVo2);
		System.out.println("SearchList"+SearchList);
		
		request.setAttribute("classVo", classVo);
		request.setAttribute("classList",classVo2);
		request.setAttribute("studentList", SearchList);
		
		request.setAttribute("viewPage", "/WEB-INF/view/MainAdmin/StudentList.jsp");
		request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
