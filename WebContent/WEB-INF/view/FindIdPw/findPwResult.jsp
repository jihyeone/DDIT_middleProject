<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//서블릿에서 저장한 데이타 꺼내기
	int cnt = (int)request.getAttribute("result");
	if(cnt != 0){
%>		
	{
		"flag" : "성공" 
	
	}	
<%	}else{ %>
	{
		
		
		"flag" : "실패"
		
	}
<%		
	}
%>