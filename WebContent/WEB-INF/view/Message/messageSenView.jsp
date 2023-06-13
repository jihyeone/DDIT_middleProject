<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.MessageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MessageVO msgvo = (MessageVO)request.getAttribute("MessageVO");
	StudentVO Loginstu = (StudentVO) session.getAttribute("LoginStudent");
	StudentVO recivestu= (StudentVO) request.getAttribute("reciVO");
%>  

<style>
.stumanage{
	width: 70%;
	height: 700px;
}
#pagging{
	position: relative;
    top: 10px;
    margin: 10px auto;
}
.stumanage{
	height: 100%;
}
form{
	margin: 10px auto;
	padding: 10px;
}
</style>


   <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">쪽지함</div>
	    	 		
  	 		  <div class="btngroup">
 	 		    <input type="button" class="btn btn-primary btn-sm mgsList" 
 	 		    		onclick="location.href='<%= request.getContextPath() %>/message/messageList.do?stuId=<%= Loginstu.getStu_id()%>'"
 	 		    		value="목록 보기">
 	 		    </div>


<!-- 받는사람 나중에 이름가져와야 함 -->

<form>
	<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label> <span class="Cnsspan">&nbsp;<%=msgvo.getMsg_ttl()%></span><br>
	<label>받는사람 : </label><span class="Cnsspan">&nbsp;<%=recivestu.getStu_nm()%></span><br>
	<label>작성날짜 : </label><span class="Cnsspan">&nbsp;<%=msgvo.getSend_date().substring(0,10)%></span><br>
    <label>작성내용 : </label><hr>
	<p><%=msgvo.getMsg_cn() %></p>
</form>


	    	 	</div>
    	 	</div>
    	 </div>
    </div>
  </div>	    

