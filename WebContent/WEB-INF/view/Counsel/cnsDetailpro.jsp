<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	// 상담 세부내용 불러오기
	CounselVO cvo = (CounselVO) request.getAttribute("cnsDetail");
	StudentVO svo = (StudentVO) request.getAttribute("svo");
	ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
	
	String check = "";
	if(cvo.getCheckyn().equals("Y")){
		check = "답변확인";
	} else{
		check = "미확인";
	}
	
%>

<script>

$(function() {
	$('.CnsList').on('click', function() {
		location.href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= cvo.getPro_id()%>&spage=1"
	})
	
	
	$('.Cnscheck').on('click', function() {
		if("<%= check %>" == "답변확인"){
			alert('이미 답변완료하였습니다.');
			$('#cnsOKModal').modal('hide');
			location.href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= Loginpro.getPro_id()%>&spage=1";
		}
	})
	
	// 상담 승인 버튼 클릭하면 메일 발송함
	$('.sendCnsOK').on('click', function() {
		vdate = $('#cnsDate').val();
		vtime = $('#selectTime option:selected').val();
		vcoment = $('#okcoment').val();
		
		console.log(vdate, vtime, vcoment);
		
	
		$.ajax({
			url : '<%=request.getContextPath() %>/counsel/ConselsendMail.do',
			data : {"pro_id" : "<%= Loginpro.getPro_id() %>", 
					"stu_id" : "<%= svo.getStu_id()%>",
					"date" : vdate, "time" : vtime,
					"coment" : vcoment, "cns_no" : "<%= cvo.getCns_no()%>"},
					
			type : 'post',
			success : function (res) {
				if(res.flag == "성공"){
					alert('상담신청 승인 완료하였습니다.');
					$('#cnsOKModal').modal('hide');
// 					vform.val("");
					location.href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= Loginpro.getPro_id()%>&spage=1";
					
				}
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
			
		});
	})
	
})
</script>

<style>
form{
	margin : 10px;
	height : 500px;
	padding: 10px;
}

label{
	font-weight: bold;
	display: inline-block;
	width: 100px;
}
</style>

    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">상담예약</div>
	    	 		
  	 		  <div class="btngroup">
 	 		    <input type="button" class="btn btn-primary btn-sm CnsList" value="목록으로 돌아가기">
 	 		    <input type="button" class="btn btn-primary btn-sm Cnscheck" value="답변하기"
 	 		    data-bs-toggle="modal" data-bs-target="#cnsOKModal">
 	 		    </div>
 	 		
 
 	 	<%
	if(cvo == null){ // 객체가 없으면

		out.println("<h4>해당 상담내역이 없습니다.</h4>");

	} else{ // 내역이 있으면
		

		
%>	
	<form>
	<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label> <span class="Cnsspan">&nbsp;<%= cvo.getCns_ttl() %></span><br>
	<label>작 성 자&nbsp; : </label><span class="Cnsspan">&nbsp;<%= svo.getStu_nm() %></span><br>
	<label>답변확인 : </label><span class="Cnsspan">&nbsp;<%= check %></span><br>
	<label>작성날짜 : </label><span class="Cnsspan">&nbsp;<%= cvo.getReg_date().substring(0,10) %></span><br>
    <label>작성내용 : </label><hr>
	<p><%= cvo.getCns_cn() %></p>
	</form>
<%
	}
%>
	    	 	</div>
    	 	</div>
    	 </div>
    </div>
  </div>	    


  <!-- The Modal -->
<div class="modal" id="cnsOKModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">상담승인</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="CnsOKForm">
        	<label>상담장소 : </label><span>314호</span><br>
       		<label>시간선택 : </label>
       			<input type="date" name="cnsDate" id="cnsDate"> 
       			<select id="selectTime">
       				<option value="15:00 ~ 16:00">15:00 ~ 16:00</option>
       				<option value="16:00 ~ 17:00">16:00 ~ 17:00</option>
       				<option value="17:00 ~ 18:00">17:00 ~ 18:00</option>
       				<option value="18:00 ~ 19:00">18:00 ~ 19:00</option>
       				<option value="19:00 ~ 20:00">19:00 ~ 20:00</option>
       				<option value="20:00 ~ 21:00">20:00 ~ 21:00</option>
       			</select><br>
        	<label>답변</label>
        	<textarea name="okcoment" id="okcoment" style="height: 200px; width: 370px;"></textarea><br>
        	<input type="button" value="제출" class="btn btn-primary btn sendCnsOK">
        	<input type="reset" value="취소" class="btn btn-primary btn cancelCns">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
      </div>

    </div>
  </div>
</div>
  
  