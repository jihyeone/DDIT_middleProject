package kr.or.ddit.middle.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.vo.LB_AttachVO;

@WebServlet("/lboard/lBoardFileDown.do")
public class LBoardFileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 파라미터로 넘어온 파일번호를 받는다.
		String lfile_no = request.getParameter("lfile_no");
		
		ILect_BoardService service = Lect_BoardServiceImpl.getInstance();
		
		LB_AttachVO fileVO = service.getlbFileNo(lfile_no);
		
		// 업로드된 파일들이 저장된 폴더 설정
		String uploadPath = "d:/d_other/Files";
//		String uploadPath = "e:/d_other/Files";
		
		// 저장될 폴더가 존재하지 않으면 새로 생성한다.
		File f = new File(uploadPath);
		if(! f.exists()) {
			f.mkdirs();	// 폴더를 생성하는 명령
		}
		
		response.setCharacterEncoding("UTF-8");
		
		// 다운 받을 파일의 File객체 생성 ==> 실제 저장된 파일명을 지정
		File downFile = new File(uploadPath, fileVO.getLfile_path());
		
		if(downFile.exists()) {	// 파일이 있을 때
			// ContentType 설정 ==> 다운로드용
			response.setContentType("application/octet-stream; charset=UTF-8");
			
			// Response객체에 content-disposition헤더 속성을 추가
			// ==> 이 속성에는 다운로드할 때 클라이언트 컴퓨터에 저장할 파일명을 지정
			String headerKey = "content-disposition";
			String headerValue = "attachment;" + getEncodedFileName(request, fileVO.getLfile_nm());
			
			response.setHeader(headerKey, headerValue);
			
			// 서버에 저장된 파일을 읽어서 클라이언트로 전송한다.
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			try {
				// 입력용 스트림 객체 생성 ==> 파일 입력용
				bin = new BufferedInputStream(new FileInputStream(downFile));
				
				// 출력용 스트림 객체 생성 ==> Response객체 이용
				bout = new BufferedOutputStream(response.getOutputStream());
				
				byte[] temp = new byte[1024];
				int len = 0;
				
				// bytem배열을 이용하여 파일 내용을 읽어서 출력용 스트림으로 출력한다.
				while((len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			} finally {
				if(bout != null) try {bout.close();}catch(IOException e) {}
				if(bin != null) try {bin.close();}catch(IOException e) {}
			}
			
		}else {	// 파일이 없을 때
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().println(fileVO.getLfile_nm() + " 파일이 존재하지 않습니다.");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getEncodedFileName(HttpServletRequest request, String lfile_path) {
		String encodedFilename = "";
		
		String userAgent = request.getHeader("User-Agent");
		try {
			// MSIE 10버전 이하의 웹브라우저 검사
			if(userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				encodedFilename = "filename=\"" 
									+ URLEncoder.encode(lfile_path, "UTF-8").replaceAll("\\+", "\\ ") 
									+ "\"";
			}else {
				encodedFilename = "filename*=UTF-8''" 
									+ URLEncoder.encode(lfile_path, "UTF-8").replaceAll("\\+", "%20");
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("지원하지 않는 인코딩 방식입니다.");
		}
		return encodedFilename;
	}

}
