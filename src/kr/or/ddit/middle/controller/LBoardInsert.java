package kr.or.ddit.middle.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.regexp.internal.recompile;

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.vo.LB_AttachVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;

@WebServlet("/lboard/lBoardInsert.do")
@MultipartConfig
public class LBoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Lect_BoardVO lbVO = new Lect_BoardVO();
		
		String lb_gu = request.getParameter("lb_gu");
		String pro_id = request.getParameter("pro_id");
		String lboard_ttl = request.getParameter("lboard_ttl");
		String lboard_cn = request.getParameter("lboard_cn");
		lboard_cn = lboard_cn.replaceAll("\n", "<br>");	// 내용 줄바꿈
		
		try {
			BeanUtils.populate(lbVO, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		System.out.println("게시글 내용 : " + lbVO.getLboard_cn());
		
		lbVO.setLb_gu(lb_gu);
		lbVO.setPro_id(pro_id);
		lbVO.setLboard_ttl(lboard_ttl);
		lbVO.setLboard_cn(lboard_cn);
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		
		int res = service.insertLBoard(lbVO);
		
		System.out.println("결과 : " + res);
		
//		--------------------------------------------------------------------------------------------
		// 첨부파일
		
		// 업로드된 파일이 저장될 폴더 설정 (서버컴퓨터 쪽) 
		String uploadPath = "d:/d_other/Files";
//		String uploadPath = "./files";
		
		// 저장될 폴더가 존재하지 않으면 새로 생성한다.
		File f = new File(uploadPath);
		if(! f.exists()) {
			f.mkdirs();	// 폴더를 생성하는 명령
		}
		
		String lboard_no = res+"";
		System.out.println("글번호 : " + lboard_no);
		
		LB_AttachVO fileVO = service.getFileLBNo(lboard_no);
		
		// 수신 받은 파일 데이터 처리하기(서버 쪽)
		String lfile_nm = "";	// 수신된 파일의 파일명이 저장될 변수

		List<LB_AttachVO> fileList = new ArrayList<LB_AttachVO>();
			
		// 전체 Part객체 개수만큼 반복 처리
		for(Part part : request.getParts()) {
			lfile_nm = extractFileName(part);
		
			if(!"".equals(lfile_nm)) {	// 파일인지 검사
				LB_AttachVO lbatVO = new LB_AttachVO();
				lbatVO.setLboard_no(lboard_no);
				lbatVO.setLfile_nm(lfile_nm);
				
				// 파일이름이 중복되는 것을 방지하기 위해서 UUID객체를 이용하여 저장할 파일명을 만든다.
				String saveFileNm = UUID.randomUUID().toString();
				lbatVO.setLfile_path(saveFileNm);	// 만들어진 저장 파일명을 VO에 셋팅
				
				// 전송된 파일의 크기는 Part객체의 getSize()메서드를 이용해서 구한다.(단위: byte)
				// part.getSize();	// 단위 변환하지 않을 때
				// byte단위의 파일 크기를 KB단위로 변환해서 VO에 셋팅
				lbatVO.setLfile_size((long)Math.ceil(part.getSize()/1024.0));
				
				try {
					part.write(uploadPath + File.separator + saveFileNm);
				} catch (Exception e) {
					e.printStackTrace();
				}
				fileList.add(lbatVO);
				
//				request.setAttribute("fileVO", lbatVO);
			}
		}
		
		for(LB_AttachVO lbatVO : fileList) {
			service.insertLBfile(lbatVO);
		}
		
		request.setAttribute("result", res);
		request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
	}

	private String extractFileName(Part part) {
		String filename = "";
		String contentDispoition = part.getHeader("content-disposition");
		String[] items = contentDispoition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				filename = item.substring(item.indexOf("=") + 2, item.length() -1);
			}
		}
		return filename;
	}
}
