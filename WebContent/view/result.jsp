<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    
    	StudentVO vo = (StudentVO) request.getAttribute("findVO");
    	
    if( vo == null) {
    	// 학생 없음

    %>
    	{
    		"flag" : "그런 학생은 없습니다."
    	}
    	
    
    
    
    <%
    } else{
    	// 학생 있고 id 반환
    %>
    
   		 {
    		"flag" : "<%= vo.getStu_id() %>"
    	}
    	
    
    <%
    }
    %>