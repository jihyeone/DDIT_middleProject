package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.List;
import java.util.PrimitiveIterator.OfDouble;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.dao.AdminDaoImpl;
import kr.or.ddit.middle.dao.IAdminDao;
import kr.or.ddit.middle.dao.IProfessorDao;
import kr.or.ddit.middle.dao.IStudentDao;
import kr.or.ddit.middle.dao.ProfessorDaoImpl;
import kr.or.ddit.middle.dao.StudentDaoImpl;
import kr.or.ddit.middle.service.AdminServiceImpl;
import kr.or.ddit.middle.service.CBoardServiceImpl;
import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.IAdminService;
import kr.or.ddit.middle.service.ICBoardService;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.service.ILect_BoardService;
import kr.or.ddit.middle.service.IMessageService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.Lect_BoardServiceImpl;
import kr.or.ddit.middle.service.MessageServiceImpl;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.AdminVO;
import kr.or.ddit.middle.vo.Comm_BoardVO;
import kr.or.ddit.middle.vo.CounselVO;
import kr.or.ddit.middle.vo.Lect_BoardVO;
import kr.or.ddit.middle.vo.MessageVO;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

@WebServlet("/LoginMain.do")
public class LoginMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//로그인 정보 받기
		String userId = request.getParameter("userid");
		String userPass = request.getParameter("userpass");
		String radio = request.getParameter("ddit");
		
		
		System.out.println(userId + userPass );
		
		
		IStudentService servicestudent = StudentServiceImpl.getInstance();
		IProfessorService servicepro = ProfessorServiceImpl.getInstance();
		IAdminService serviceadmin = AdminServiceImpl.getInstance();
		IMessageService mgsService = MessageServiceImpl.getInstance();
		ICounselService cnsSerivuce = CounselServiceImpl.getInstance();
		ICBoardService CBService = CBoardServiceImpl.getInstance();
		ILect_BoardService LBService = Lect_BoardServiceImpl.getInstance();
		
		
		//로그인 정보 VO저장
		StudentVO stdVo = new StudentVO();
		stdVo.setStu_id(userId);
		stdVo.setStu_pw(userPass);
		
		ProfessorVO pfsVo = new ProfessorVO();
		pfsVo.setPro_id(userId);
		pfsVo.setPro_pw(userPass);
		
		AdminVO admVo = new AdminVO();
		admVo.setAdmin_id(userId);
		admVo.setAdmin_pw(userPass);
		
		
		System.out.println(radio);
		
		//ibatis
		StudentVO loginStudent = new StudentVO();
		loginStudent = servicestudent.getStudent(stdVo);
		
		ProfessorVO loginProfessor = new ProfessorVO();
		loginProfessor = servicepro.getProfessor(pfsVo);
		
		AdminVO loginAdmin = new AdminVO();
		loginAdmin = serviceadmin.getAdmin(admVo);
		
		
		// 학생 로그인 메인화면 커뮤니티 게시판 최근 글 리스트
		List<Comm_BoardVO> cBoardList = CBService.recentCBoardList();
		
		// 학생 로그인 메인화면 강의게시판 최근 글 리스트
		List<Lect_BoardVO> lBoardList = LBService.recentLBoardList();
		
		
		HttpSession session = request.getSession();
		
		// 받은 메세지 갯수
		int msgCount = 0;
		
		// 관리자 메인페이지에 학생 리스트 정보 꺼내오기
		List<StudentVO> stulist = servicestudent.getAllstudent();
		
		// 관리자 교수 신규 등록시 교수 새로운 아이디 추가
		String newproId = serviceadmin.newproId();
//		System.out.println("교수 등록시 아이디 뒷번호" + newproId);
		
		//------------------------------------------------------
		
		StudentVO mgsStuInfo = new StudentVO();
		
		//radio 체크
		if("학생".equals(radio) && loginStudent!=null) {
			// 학생이 받은 최신 쪽지 출력
			MessageVO mgsvo = null;
			
			mgsvo = (MessageVO) mgsService.selectRecntMgs(loginStudent.getStu_id());
			
			// 메세지 보낸 학생의 정보
			
			//학생
				if(mgsvo != null) {
					mgsStuInfo = servicestudent.getone(mgsvo.getSend_id());
					msgCount = mgsService.countNewMsg(userId);
					loginStudent.setStu_NewmsgCnt(msgCount);
					
					request.setAttribute("stuMessage", mgsvo);
					request.setAttribute("sendMgsStuInfo", mgsStuInfo);
//					session.setAttribute("LoginStudent", loginStudent);
				} else {
					MessageVO mgsvonull = new MessageVO();
					mgsvonull.setMsg_ttl("null");
					StudentVO mgsStuInfonull = new StudentVO();
					mgsStuInfonull.setStu_nm("null");
					// 받은 메세지가 없으면 null인 메세지를 생성해서 넣음
					request.setAttribute("stuMessage", mgsvonull);
					// 메세지 받은 학생이 없으니 없는 학생으로 넣음
					request.setAttribute("sendMgsStuInfo", mgsStuInfonull);
				}
				
				request.setAttribute("lBoardList", lBoardList);
				request.setAttribute("cBoardList", cBoardList);
				session.setAttribute("LoginStudent", loginStudent);
				request.getRequestDispatcher("/layout/layoutStu.jsp").forward(request, response);
//				response.sendRedirect(request.getContextPath()+"/layout/layoutStu.jsp");

		}else if("교직원".equals(radio) && loginProfessor!=null) {
			// 교수가 받은 상담
				CounselVO cnsvo = null;
				cnsvo = cnsSerivuce.selectRecentCns(loginProfessor.getPro_id());
				
				List<CounselVO> recentCnsList = cnsSerivuce.recentCnsList(loginProfessor.getPro_id());
				
				
				if(cnsvo != null) {
					// 상담 받은 학생의 정보
					mgsStuInfo = servicestudent.getone(cnsvo.getStu_id());
					System.out.println("상담받은 학생 이름 = " + mgsStuInfo.getStu_nm());
					
					msgCount = cnsSerivuce.countCnsCheckN(loginProfessor.getPro_id());
					loginProfessor.setPro_NewCnsCnt(msgCount);
					
					request.setAttribute("CnsRecent", cnsvo);
					request.setAttribute("CnsRecnetStu", mgsStuInfo);
				} else {
					CounselVO cnsvonull = new CounselVO();
					cnsvonull.setCns_ttl("null");
					StudentVO mgsStuInfonull = new StudentVO();
					mgsStuInfonull.setStu_nm("null");
					request.setAttribute("CnsRecent", cnsvonull);
					request.setAttribute("CnsRecnetStu", mgsStuInfonull);
				}
				request.setAttribute("lBoardList", lBoardList);
				request.setAttribute("recentCnsList", recentCnsList);
				session.setAttribute("LoginProfessor", loginProfessor);
				request.getRequestDispatcher("/layout/layoutPro.jsp").forward(request, response);
//				response.sendRedirect(request.getContextPath()+"/layout/layoutPro.jsp");
//			}
			
		}else if(radio==null && loginAdmin!=null) {
				loginAdmin.setNewMgsCnt(0);
				
				session.setAttribute("LoginAdmin", loginAdmin);
				session.setAttribute("list", stulist);
				session.setAttribute("newproId", newproId);
//				response.sendRedirect(request.getContextPath()+"/layout/layoutAdim.jsp");
				request.getRequestDispatcher("/layout/layoutAdim.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/Login/LoginMain.jsp").forward(request, response);
//			response.sendRedirect(request.getContextPath()+"/Login/LoginMain.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
