<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	// 상담 목록 세부내용 보기
	CounselVO cvo = (CounselVO)request.getAttribute("cnsDetail");
	// 상담 디테일 객체
	ProfessorVO provo = (ProfessorVO) request.getAttribute("provo");
	
	
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
		location.href="<%= request.getContextPath()%>/counsel/CounselListstu.do?stuId=<%= cvo.getStu_id()%>&spage=1"
	})
	
	
	
	$('.Cnscancel').on('click', function() {

		if("<%= check %>" == "미확인"){
			$.ajax({
				url : '<%=request.getContextPath() %>/counsel/ConselDelete.do',
				data : {"cns_no" : <%= cvo.getCns_no() %>},
				type : 'post',
				success : function(res) {
					alert('상담요청 내역이 취소되었습니다.')
					location.href="<%= request.getContextPath()%>/counsel/CounselListstu.do?stuId=<%= cvo.getStu_id()%>&spage=1"
				},
				error : function(xhr){
					alert("오류 : " + xhr.status);
				},
				dataType : 'json'
			})
		} else{
			alert('답변받은 상담내역은 취소하실 수 없습니다.');
			location.href="<%= request.getContextPath()%>/counsel/CncDetailstu.do?cnsNo=<%= cvo.getCns_no()%>"
		}
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
 	 		    <input type="button" class="btn btn-primary btn-sm Cnscancel" value="상담취소">
 	 		    </div>
 	 		
 
 	 	<%
	if(cvo == null){ // 객체가 없으면

		out.println("<h4>해당 상담내역이 없습니다.</h4>");

	} else{ // 내역이 있으면
		

		
%>	
	<form>
	<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label> <span class="Cnsspan">&nbsp;<%= cvo.getCns_ttl() %></span><br>
	<label>상담교수 : </label><span class="Cnsspan">&nbsp;<%= provo.getPro_nm() %></span><br>
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

  