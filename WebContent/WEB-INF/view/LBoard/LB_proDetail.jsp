<%@page import="kr.or.ddit.middle.vo.LB_AttachVO"%>
<%@page import="kr.or.ddit.middle.controller.LBoardDelete"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.LB_CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.Lect_BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	Lect_BoardVO lbVO = (Lect_BoardVO) request.getAttribute("LBDetail");
	LB_CodeVO codeVO = (LB_CodeVO) request.getAttribute("codeVO");
	ProfessorVO proVO = (ProfessorVO) request.getAttribute("proVO");
	LB_AttachVO fileVO = (LB_AttachVO) request.getAttribute("fileVO");
	
	List<LB_CodeVO> cdList = (List<LB_CodeVO>) request.getAttribute("cdList");
	
	ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
	
%>

<style>
label{
/* 	font-size: 1.3em; */
	font-weight: bold;
	display: inline-block;
	width: 100px;
}
a{
	text-decoration: none;
}
#cndiv{
	margin-left: 15px;
}
</style>

<script type="text/javascript">
$(function(){
	
	$('.LbList').on('click', function(){
		location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?proId=<%= Loginpro.getPro_id()%>&spage=1";
	})
	
	$('#modifyBtn').on('click', function(){
		mdformData = new FormData($('#LBmdform')[0]);
// 		vlbmdform = $('#LBmdform').serialize();
		$.ajax({
			url : '<%= request.getContextPath() %>/lboard/lBoardModify.do',
// 			data : vlbmdform,
			data : mdformData,
			type : 'post',
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
			success : function(res){
// 				alert(res.flag);
				if(res.flag == "성공") {
					alert("게시글 수정이 완료되었습니다.");
					location.href="<%= request.getContextPath() %>/lboard/lBoardProDetail.do?lboard_no=<%= lbVO.getLboard_no() %>"
				}
				$('#lbmdModal').modal('hide');
				$('#LBmdform .txt').val("");
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
	})

	$('.LbDelete').on('click', function(){
		$.ajax({
			url : '<%= request.getContextPath() %>/lboard/lBoardDelete.do',
			data : {"lboard_no" : <%= lbVO.getLboard_no() %>},
			type : 'post',
			success : function(res){
// 				alert(res.flag);
				if(res.flag == "성공"){
				alert("게시글이 삭제되었습니다.");
				location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?proId=<%= Loginpro.getPro_id()%>&spage=1";
				}
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
	})

})

</script>

	<div class="container-content container mt-3">
		<div class="row">
			<div class="row col-md-12">
			<div class="frame content">
			<div class="stumanage">
				<div class="title stuttl">강의게시판</div>
					<%
					if(Loginpro.getPro_id().equals(proVO.getPro_id())){
					%>
					<div class="btngroup">
					<input type="button" class="btn btn-primary btn-sm LbList" value="목록으로 돌아가기">
	 	 		    <input type="button" class="btn btn-primary btn-sm LbModify" value="수정" data-bs-toggle="modal" data-bs-target="#lbmdModal">
	 	 		    <input type="button" class="btn btn-primary btn-sm LbDelete" value="삭제">
					</div>
					<%	
					}else{
					%>
						<div class="btngroup">
						<input type="button" class="btn btn-primary btn-sm LbList" value="목록으로 돌아가기">
						</div>
					<%
					}
					%>
				<%
				if(lbVO == null) {
					out.println("<h3>해당 게시글이 없습니다.</h3>");
				}else {
				%>
					<form name="lbDetailForm">
					<br>
					<label>&nbsp;&nbsp;분&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;류 : </label><span class="Lbspan">&nbsp;&nbsp;<%= codeVO.getLb_nm() %></span><br>
					<label>&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label><span class="Lbspan">&nbsp;&nbsp;<%= lbVO.getLboard_ttl() %></span><br>
					<label>&nbsp;&nbsp;작 성 자 : </label><span class="Lbspan">&nbsp;&nbsp;<%= proVO.getPro_nm() %></span><br>
					<label>&nbsp;&nbsp;이 메 일 : </label><span class="Lbspan">&nbsp;&nbsp;<%= proVO.getPro_mail() %></span><br>
					<label>&nbsp;&nbsp;날&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;짜 : </label><span class="Lbspan">&nbsp;&nbsp;<%= lbVO.getLboard_date().substring(0, 10) %></span><br>
					<label>&nbsp;&nbsp;조 회 수 : </label><span class="Lbspan">&nbsp;&nbsp;<%= lbVO.getLboard_rc() %></span><br>
					<label>&nbsp;&nbsp;내&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;용 : </label><br><div class="Lbspan" id="cndiv"><%= lbVO.getLboard_cn() %></div>
					<hr>
					<label>&nbsp;&nbsp;첨부파일</label><br>
					<%
					if(fileVO==null) { 
					%>
						<span>&nbsp;&nbsp;&nbsp;첨부 파일이 없습니다.</span>					
					<%
					}else { 
					%>
						<input type="hidden" name="lfile_no" value="<%= fileVO.getLfile_no() %>">
						<span>&nbsp;&nbsp;<%= fileVO.getLfile_nm() %></span>&nbsp;&nbsp;
						<a href="<%= request.getContextPath() %>/lboard/lBoardFileDown.do?lfile_no=<%= fileVO.getLfile_no() %>">[DownLoad]</a>
						<br>
					<%
					} 
					%>
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
	<div class="modal" id="lbmdModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">게시글 수정</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        <form id="LBmdform" enctype="multipart/form-data" method="post">
        	<label>분    류</label>
        	<select id="selectgu" name="selectgu">
        	<%
        		for(LB_CodeVO clist : cdList){
        			out.print("<option value='" + clist.getLb_gu() + "'>" + clist.getLb_nm() + "</option>");
        		}
        	%>
        	</select><br>
        	
        	<label>제    목</label>
        	<input type="text" name="lboard_ttl" class="txt" value="<%= lbVO.getLboard_ttl() %>"><br>
        	
        	<label>작 성 자</label>
			<input type="text" name="pro_nm" value="<%= Loginpro.getPro_nm() %>" disabled><br>
			
			<label>이 메 일</label>
			<input type="text" name="pro_nm" class="txt" value="<%= Loginpro.getPro_mail() %>" disabled><br>
        	
        	<label>본    문</label><br>
        	<textarea rows="10" cols="50" name="lboard_cn" class="txt" ><%= lbVO.getLboard_cn() %></textarea><br>
        	
			<input type="hidden" name="pro_id" value="<%= Loginpro.getPro_id() %>">    
			<input type="hidden" name="lboard_no" value="<%= lbVO.getLboard_no() %>">
<%-- 			<input type="hidden" name="lfile_no" value="<%= fileVO.getLfile_no() %>"> --%>
			    	
        	<br>
        	
        	<input type="submit"  class="btn btn-primary btn-sm" value="확인" id="modifyBtn">
        	</form>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>