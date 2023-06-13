package kr.or.ddit.middle.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.middle.service.CounselServiceImpl;
import kr.or.ddit.middle.service.ICounselService;
import kr.or.ddit.middle.service.IProfessorService;
import kr.or.ddit.middle.service.IStudentService;
import kr.or.ddit.middle.service.ProfessorServiceImpl;
import kr.or.ddit.middle.service.StudentServiceImpl;
import kr.or.ddit.middle.vo.ProfessorVO;
import kr.or.ddit.middle.vo.StudentVO;

/**
 * Servlet implementation class ConselsendMail
 */
@WebServlet("/counsel/ConselsendMail.do")
public class ConselsendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConselsendMail() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	
	HttpSession session = request.getSession();
	ProfessorVO LoginProfessor = (ProfessorVO)session.getAttribute("LoginProfessor");
	
	
	String getTime = request.getParameter("time");
	String getDay = request.getParameter("date");
	String getcoment  = request.getParameter("coment");
	
	// 보낼 메일 = 교수 메일
	String proId = request.getParameter("pro_id");
	IProfessorService proService = ProfessorServiceImpl.getInstance();
	ProfessorVO pvo = proService.professerone(proId);
	String sendMail = pvo.getPro_mail();
	
	int res = 0;
	
	// 받는 메일 = 학생메일
	String stuId = request.getParameter("stu_id");
	IStudentService stuService = StudentServiceImpl.getInstance();
	StudentVO svo = stuService.getone(stuId);
	String receviceMail  = svo.getStu_mail();
	
	// 답변확인 update
	ICounselService cnsServier = CounselServiceImpl.getInstance();
	String cns_no = request.getParameter("cns_no");
	
	System.out.println("보내는 메일 : " + sendMail + "/ 받는 메일 : " + receviceMail);
	
	
	// 받을 메일
	String recipient = "kjh87272@gmail.com";
	
    // 1. 발신자의 메일 계정과 비밀번호 설정
    final String user = "dlgpdus421@gmail.com";
    final String password = "itfkueovypvupyoo";
	
    
    // 2. Property에 SMTP 서버 정보 설정
    
    Properties prop = new Properties();
    prop.put("mail.smtp.host", "smtp.gmail.com");
    prop.put("mail.smtp.port", 465);
    prop.put("mail.smtp.auth", "true");
    prop.put("mail.smtp.ssl.enable", "true");
    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	
    // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
    Session session2 = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
    	protected PasswordAuthentication getPasswordAuthentication() {
    		return new PasswordAuthentication(user, password);
    	}
    });
    
    MimeMessage message = new MimeMessage(session2);
    
    
    try {
    	message.setFrom(new InternetAddress(user, pvo.getPro_nm()+" 교수","utf-8"));
    	
    	// 수신자 메일 주소
    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    	
    	// Subject
    	message.setSubject("상담확인");
    	
    	// Text
    	message.setText("상담 장소 : 314호\n" 
    				  + "상담 가능 시간은 [" + getDay + "] [" + getTime+"] 입니다.\n"
    				  + getcoment);
    	
    	Transport.send(message);    // send message
    	
    	// 승인 체크
    	res = cnsServier.updateCheck(cns_no);
    	
    	// 승인하지 않은 count 세기
    	int msgCount = cnsServier.countCnsCheckN(LoginProfessor.getPro_id());
    	LoginProfessor.setPro_NewCnsCnt(msgCount);
    	
    	session.setAttribute("LoginProfessor", LoginProfessor);
    	request.setAttribute("result", res);
    	request.getRequestDispatcher("/WEB-INF/view/result/result.jsp").forward(request, response);
    	
    } catch (AddressException e) {
    	e.printStackTrace();
    } catch (MessagingException e) {
    	e.printStackTrace();
    }
    
    
	}
	
	
	

}
