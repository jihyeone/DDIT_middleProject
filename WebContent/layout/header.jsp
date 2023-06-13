<%@page import="kr.or.ddit.middle.vo.AdminVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%-- /layout/header.jsp 문서 공통 --%> 
    
<%	
	StudentVO LoginStudent = (StudentVO)session.getAttribute("LoginStudent");
	ProfessorVO LoginProfessor = (ProfessorVO)session.getAttribute("LoginProfessor");
	AdminVO LoginAdmin = (AdminVO)session.getAttribute("LoginAdmin");
	
%>
 
<style>
.newmsg{
	position : relative;
	top : 3px;
	right : 60px;
/* 	display: block; */
	float: right;
}
</style>


         <div class="container-fluid frame top">
                <div class="frame head">
  	
  		         
                     <% if(LoginStudent != null ){ %> 
<!--                    		 <i class="bi bi-envelope-exclamation fs-3" id="memo"></i>  -->
                   		 <%   if(LoginStudent.getStu_NewmsgCnt() >0){ %>
                   		 <span class="badge rounded-circle bg-danger newmsg"  style="text-align: center;"><%= LoginStudent.getStu_NewmsgCnt() %></span>
                   		 <%  } %>
                   		 <i class="bi bi-envelope fs-3" id="memo" style="margin-right: 50px;" ></i>
                 	     <%  } %>
                     
                     
                    <% if (LoginProfessor != null) {  %>   
                     	<%   if(LoginProfessor.getPro_NewCnsCnt() >0){ %>
                   		 <span class="badge rounded-circle bg-danger newmsg"  style="text-align: center;"><%= LoginProfessor.getPro_NewCnsCnt() %></span>
                   		 <%  } %>
                   		 <i class="bi bi-envelope fs-3" id="Cns" style="margin-right: 50px;" ></i>
                     
                     <%
                     }
                     %>
                     
                    <i class="bi bi-box-arrow-in-right fs-3" id="logoutbtn" ></i>
<!--                     <i class="bi bi-box-arrow-in-right fs-3" id="logoutbtn" style="margin-right: 50px;"></i> -->
                    <i class="bi bi-house-door-fill fs-3" id="homebtn"></i>
                    
                     
                </div>
                <div class="frame navi">
                    <div id="navi">
                        <ul class="nav justify-content-center headnavi">
                            <li class="nav-item">
                              <a class="nav-link" href="#" id="lect" onclick="lboard();">강의게시판</a>
                            </li>
                            <li class="nav-item">
                              <a class="nav-link" 
                             	 href="<%=request.getContextPath() %>/board/CommBoard.do?spage=1" id="comm">커뮤니티게시판</a>
                            </li>
                            <li class="nav-item">
                              <a class="nav-link" href="#" onclick="counsel();">상담</a>
                            </li>
                          </ul>
                        </div>
                </div>
            </div>
            
            