<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 학생 메뉴 --%>
<%-- /layout/leftStudent.jsp --%>

            <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top"  id="sideNav">
                <a class="navbar-brand js-scroll-trigger" href="#page-top">
                    <span class="d-block d-lg-none">Admin</span>
                    <span class="d-none d-lg-block"><img class="img-fluid img-profile rounded-circle mx-auto mb-2" src="<%= request.getContextPath() %>/images/admin.png" alt="..." /></span>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div id="name">관리자</div>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav">
                    <br>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                       		href="<%=request.getContextPath()%>/managerStudent.do">학생관리</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="<%=request.getContextPath()%>/ManagerProfessor.do">교수관리</a></li>
<!--                         <li class="nav-item"><a class="nav-link js-scroll-trigger" href="#interests">강의관리</a></li> -->
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="<%= request.getContextPath()%>/schedule/ScheduleAdmin.do">강의시간표작성</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" 
                       		href="<%=request.getContextPath()%>/ClassList.do">학급관리</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="<%=request.getContextPath()%>/RoomList.do?spage=1">강의실관리</a>
                    </ul>
                </div>
            </nav>