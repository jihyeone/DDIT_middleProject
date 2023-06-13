<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    
<% 
	StudentVO Loginstu = (StudentVO) session.getAttribute("LoginStudent");
	System.out.println( "-----"+Loginstu);
%>    
    
    
<%-- 학생 메뉴 --%>
<%-- /layout/leftStudent.jsp --%>
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"  id="sideNav">
                <a class="navbar-brand js-scroll-trigger" href="#page-top">
                    <span class="d-block d-lg-none"></span>
                    <span class="d-none d-lg-block"><img class="img-fluid img-profile rounded-circle mx-auto mb-2" 
                    	src="<%= request.getContextPath() %>/Info/StuImageView.do?stuId=<%= Loginstu.getStu_id() %>" alt="..." /></span>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div id="name"><%= Loginstu.getClass_no()%>_<%= Loginstu.getStu_nm() %></div><br>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="<%= request.getContextPath() %>/Info/StuInfoList.do?stuId=<%= Loginstu.getStu_id() %>">프로필</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="<%= request.getContextPath() %>/lboard/lBoardList.do?spage=1">강의게시판</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath()%>/schedule/ScheduleList.do?stuId=<%= Loginstu.getStu_id() %>">강의시간표</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath() %>/counsel/CounselListstu.do?stuId=<%= Loginstu.getStu_id() %>&spage=1">상담예약</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                       		href="<%= request.getContextPath() %>/board/MyComboardList.do?stuId=<%= Loginstu.getStu_id() %>">내가쓴글</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath() %>/message/messageList.do?stuId=<%= Loginstu.getStu_id()%>">쪽지</a></li>
                    </ul>
                </div>
            </nav>