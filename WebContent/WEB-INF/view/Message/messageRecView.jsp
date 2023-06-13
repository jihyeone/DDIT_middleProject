<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.MessageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	MessageVO msgvo = (MessageVO)request.getAttribute("MessageVO");
	StudentVO Loginstu = (StudentVO) session.getAttribute("LoginStudent");
	int res =(Integer) request.getAttribute("res");
	
	StudentVO sendVO = (StudentVO) request.getAttribute("sendVO");
	
%>  


<script>
	$(function(){
		$('.insertMsg').on('click',function(){
			vmsgform = $('#MsgForm').serialize();
			
<%-- 			vmsgform += "&send_id=" + "<%=Loginstu.getStu_id()%>" +"&reciv_id="+"<%=msgvo.getReciv_id()%>; --%>
			$.ajax({
				url: '<%=request.getContextPath()%>/messageInsert.do',
				data: vmsgform,
				type: 'post',
				success: function(res){
					alert("쪽지 전송 완료");
					location.href="<%= request.getContextPath() %>/message/messageList.do?stuId=<%= Loginstu.getStu_id()%>";
				},
				error: function(xhr){
					alert("오류: "+ xhr.status);
				},
				dataType: 'json'
				
			}) // $.ajax
		}) // insertMsg
	}) // $function
</script>

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
 	 		    <input type="button" class="btn btn-primary btn-sm Cnscheck" value="답장하기"
 	 		    data-bs-toggle="modal" data-bs-target="#myModal">
 	 		    </div>
 	 		


<!-- 보낸사람 나중에 이름가져와야 함 -->

<form>
	<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label> <span class="Cnsspan">&nbsp;<%=msgvo.getMsg_ttl()%></span><br>
	<label>보낸사람 : </label><span class="Cnsspan">&nbsp;<%=sendVO.getStu_nm()%></span><br>
	<label>작성날짜 : </label><span class="Cnsspan">&nbsp;<%=msgvo.getSend_date().substring(0,10)%></span><br>
    <label>작성내용 : </label><hr>
	<p><%=msgvo.getMsg_cn() %></p>
</form>




	    	 	</div>
    	 	</div>
    	 </div>
    </div>
  </div>	    




<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <div class="modal-title"><h3>답장하기</h3></div>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
<form id="MsgForm">
	<input type="hidden" name="send_id" value="<%= msgvo.getSend_id()%>">
	<input type="hidden" name="reciv_id" value="<%= msgvo.getReciv_id()%>">
    <p><input type="text" size=45 maxlength=45 name="msg_ttl" placeholder="제목" id="msg_ttl"></p>
    <p><textarea cols="45" rows="12" name="msg_cn" placeholder="내용" id="msg_cn"></textarea></p>
</form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary insertMsg" data-bs-dismiss="modal">전송</button>
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">취소</button>
      </div>

    </div>
  </div>
</div>



