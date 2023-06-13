<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 교수 메뉴 --%>
<%-- /layout/leftProfessor.jsp --%>

<%
	ProfessorVO loginPro = (ProfessorVO) session.getAttribute("LoginProfessor");
%>

            <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"  id="sideNav">
                <a class="navbar-brand js-scroll-trigger" href="#page-top">
                    <span class="d-block d-lg-none"></span>
                    <span class="d-none d-lg-block"><img class="img-fluid img-profile rounded-circle mx-auto mb-2" 
                    	  src="<%= request.getContextPath() %>/Info/ProImageView.do?proId=<%= loginPro.getPro_id()%>" alt="..." /></span>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div id="name"><%= loginPro.getPro_nm() %>&nbsp;교수</div><br>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath() %>/Info/proView.do?proId=<%=loginPro.getPro_id() %>">프로필</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath() %>/classListPro.do?proId=<%= loginPro.getPro_id() %>">담당학급</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath() %>/lboard/lBoardProList.do?proId=<%= loginPro.getPro_id() %>&spage=1">강의게시판</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                        	href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= loginPro.getPro_id() %>&spage=1">상담요청확인</a></li>
                    </ul>
                </div>
            </nav>