<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.MessageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	List<MessageVO> recilist = (List<MessageVO>)request.getAttribute("recilist");	// 받은 쪽지 목록
	List<MessageVO> sendlist = (List<MessageVO>)request.getAttribute("sendlist");	// 보낸 쪽지 목록
	List<StudentVO> stdList = (List<StudentVO>)request.getAttribute("stdList");
	StudentVO Loginstu = (StudentVO)session.getAttribute("LoginStudent");	// 세션에 저장된 로그인 중인 학생 정보

%>


<script>

// 선택하면 받는 사람 창에 들어가는 함수
function proc(result){
	var strText = $(result).html();
	$('#send_id').val(strText);
}


	$(function(){
		$(document).on('click', '#searchButton', function(){
			search = $('#send_id').val().trim();
			$.ajax({
				url: '<%=request.getContextPath()%>/studentSearch.do',
				data: {'search' : search},
				type: 'post',
				success: function(res){
					code = ``;
					
				$.each(res, function(i, v){
					code += `<a  href="#" onclick="proc(this)" value="">\${v.stu_id}</a>&nbsp;\${v.stu_nm}<br>`;
				})
				code += `<br>`;
				$('#resultBox').html(code);
					
				},
				error: function(xhr){
					alert("오류: "+xhr.status);
				},
				dataType: 'json'
				
			})
			
		})
		
		
	$('.insertMsg').on('click',function(){
			vmsgform = $('#MsgForm').serialize();
			vmsgform += "&reciv_id=" + "<%=Loginstu.getStu_id()%>";
			
			$.ajax({
				url: '<%=request.getContextPath()%>/messageInsert.do',
				data: vmsgform,
				type: 'post',
				success: function(res){
					alert("쪽지 전송 완료");
					location.href="<%= request.getContextPath() %>/message/messageList.do?stuId=<%= Loginstu.getStu_id()%>";
				},
				error: function(xhr){
					alert("오류: "+xhr.status);
				},
				dataType: 'json'
				
			})
			
		})
	})
	
</script>


<style>
.stumanage{
	width: 70%;
}
#pagging{
	position: relative;
    top: 10px;
    margin: 10px auto;
}
.stumanage{
	height: 100%;
}
</style>


    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">쪽지함</div>
	    	 		  <div class="btngroup">
	    	 		    <input type="button" class="btn btn-primary btn-sm msgBtn" 
	    	 		           data-bs-toggle="modal" data-bs-target="#myModal" value="쪽지쓰기" >
	    	 		    </div>
                        <div id="list">


<!-- <div><h3>쪽지함<button id="sub" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">쪽지쓰기</h3></button> -->
<input type="button"  class="btn btn-dark" value="받은 쪽지" disabled style="margin-bottom: 20px;">

 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
	<tr>
		<td>번호</td>
		<td>보낸 사람</td>
		<td style="width: 800px;">제목</td>
		<td>날짜</td>
		<td>열람</td>
	</tr>
	</thead>
<%
	if(recilist != null){
		for(MessageVO msgvo : recilist){
			String read = "";
			if(msgvo.getReadyn().equals("Y")){
				read = "읽음";
			} else{
				read = "읽지않음";
			}
%>
	<tr>
		<td><%=msgvo.getMsg_no() %></td>
		<td><%=msgvo.getSend_nm()%></td>
		<td><a href="<%=request.getContextPath() %>/messageRecView.do?msg_no=<%=msgvo.getMsg_no() %>" ><%=msgvo.getMsg_ttl() %></a></td>
		<td><%=msgvo.getSend_date().substring(0,10) %></td>
		<td><%=	read %></td>
	</tr>
<%  
	}
} else{
%>

	<tr>
		<td colspan="5">받은 쪽지가 없습니다.</td>
	</tr>

<%
}
%>

</table>
<!-- 	<div id="pagging">페이징</div> -->
</div>

	  <div id="list">
<div>
<input type="button"  class="btn btn-dark" value="보낸 쪽지" disabled style="margin-bottom: 20px;">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
	<tr>
		<td>번호</td>
		<td>받는 사람</td>
		<td style="width: 800px;">제목</td>
		<td>날짜</td>
		<td>열람</td>
	</tr>
	</thead>
<% 
	if(sendlist != null){
		for(MessageVO msgvo : sendlist){
			String msgNo = msgvo.getMsg_no();
			String read = "";
			if(msgvo.getReadyn().equals("Y")){
				read = "읽음";
			} else{
				read = "읽지않음";
			}
%>
	<tr>
		<td><%=msgvo.getMsg_no()%></td>
		<td><%=msgvo.getReciv_nm()%></td>
		<td><a href="<%=request.getContextPath() %>/messageSenView.do?msg_no=<%=msgvo.getMsg_no()%>"><%=msgvo.getMsg_ttl()%></a></td>
		<td><%=msgvo.getSend_date().substring(0,10) %></td>
		<td><%= read %></td>
	</tr>


<%  
	}	
} else{
%>

	<tr>
		<td colspan="5">보낸 쪽지가 없습니다.</td>
	</tr>

<%
}
%>
</table>
<!-- <div id="pagging">페이징</div> -->
</div>
</div>
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
        <div class="modal-title"><h3>쪽지 쓰기</h3></div>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
<div class="modal-body">
	<form id="MsgForm">
		<p><input type="text" name="send_id" placeholder="받는 사람" id="send_id">
		   <input type="button" id="searchButton" value="🔎"></p>
		   <div id="resultBox"></div>
      	<p><input type="text" size=48 maxlength=45 name="msg_ttl" placeholder="제목" id="msg_ttl"></p>
    	<p><textarea cols="50" rows="12" name="msg_cn" placeholder="내용" id="msg_cn"></textarea></p>
	</form>
</div>

      <!-- Modal footer -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary insertMsg" data-bs-dismiss="modal">전송</button>
        <button type="reset" class="btn btn-primary" data-bs-dismiss="modal">취소</button>
      </div>

    </div>
  </div>
</div>
