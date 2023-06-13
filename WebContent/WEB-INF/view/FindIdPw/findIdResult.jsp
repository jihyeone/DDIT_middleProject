<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//서블릿에서 저장한 데이타 꺼내기
	String data = (String)request.getAttribute("result");
	if(data != null){
%>		
	{
		"flag" : "<%=data %>" 
	
	}	
<%	}else{ %>
	{
		
		
		"flag" : "실패"
		
	}
<%		
	}
%>