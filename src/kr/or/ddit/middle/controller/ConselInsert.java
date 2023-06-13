package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class ConselInsert
 */
@WebServlet("/counsel/ConselInsert.do")
public class ConselInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String cns_cn = request.getParameter("cns_cn");
		cns_cn = cns_cn.replaceAll("\n", "<br>");
		System.out.println(cns_cn);
		
		CounselVO vo = new CounselVO(); 
		String stu_id = request.getParameter("stu_id");
		String pro_id = request.getParameter("pro_id");
		
		System.out.println("학생ID : " + stu_id + " / 교수아이디 : " + pro_id);
		
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("상담내용 : " + vo.getCns_cn());
		
		System.out.println("학생 id : " + stu_id );
		vo.setStu_id(stu_id);
		vo.setPro_id(pro_id);
		vo.setCns_cn(cns_cn);
		ICounselService service = CounselServiceImpl.getInstance();
		
		
		int res = service.insertCounsel(vo);
		
		System.out.println("성공은 ?? " + res);
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
		
	
	}

}
