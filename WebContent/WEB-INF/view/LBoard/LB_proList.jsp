<%@page import="kr.or.ddit.middle.vo.LB_AttachVO"%>
<%@page import="kr.or.ddit.middle.vo.PageVO"%>
<%@page import="kr.or.ddit.middle.vo.LB_CodeVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.Lect_BoardVO"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	// 서블릿에서 받은 자료
	// 게시글
	List<Lect_BoardVO> list = (List<Lect_BoardVO>) request.getAttribute("list");
	List<LB_CodeVO> codeList = (List<LB_CodeVO>) request.getAttribute("codeList");
	List<ProfessorVO> proList = (List<ProfessorVO>) request.getAttribute("proList");	
	
	ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
	
	// 첨부파일
	LB_AttachVO fileVO = (LB_AttachVO) request.getAttribute("fileVO");
// 	List<LB_AttachVO> fileList = (List<LB_AttachVO>) request.getAttribute("fileList");


	PageVO pagevo = (PageVO) request.getAttribute("pagevo");
    int currentPage = (int) request.getAttribute("currentPage");

    System.out.println("리스트 정보 = "+ list);
 	System.out.println("페이지 정보=" + pagevo);
 	System.out.println("현재페이지 정보=" + currentPage);
 	System.out.println("스타트페이지 정보=" + pagevo.getStartPage());


%>

<style type="text/css">
.form{
	margin: 0 auto;
}
.td{
 	padding: 20px;
}
table{
	text-align: center;
}
#stype{
	width: 70%;
}
label {
	display: inline-block;
	width: 70px;
}
#wform{
	margin-left: 30px;
}
</style>

<script type="text/javascript">
$(function(){
	
	$('.lbinsert').on('click', function(){
		formData = new FormData($('#LBform')[0]);
		$.ajax({
			url : '<%= request.getContextPath() %>/lboard/lBoardInsert.do',
			data : formData,
			type : 'post',
			enctype : 'multipart/form-data',
			contentType : false,
			processData : false,
			success : function(res){
//  				alert(res.flag);
				if(res.flag == "성공") {
					alert("게시글이 등록되었습니다.");
					location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?pro_id=<%= Loginpro.getPro_id() %>&spage=1";
				} else{
					alert("게시글 등록에 실패하였습니다.");
					location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?pro_id=<%= Loginpro.getPro_id() %>&spage=1";
				}
				$('#lbModal').modal('hide');
				$('#LBform .txt').val("");
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	//---------------------------------------------------------------------------------------
 
	// 페이징 번호 클릭시 다음페이지
	$(document).on('click','.pagination a.pnum', function(){
		currentPage = $(this).text().trim();
		location.href=href="<%= request.getContextPath() %>/lboard/lBoardProList.do?pro_id=<%= Loginpro.getPro_id() %>&spage=" + currentPage;
	})
	
 	// 다음 버튼 클릭 이벤트
	$(document).on('click', 'a.next' , function() {
	//alert(parseInt($('a.pnum').last().text().trim()) +1); 
		currentPage = parseInt($('a.pnum').last().text().trim()) + 1;
		location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?pro_id=<%= Loginpro.getPro_id() %>&spage=" + currentPage;
	})
	
	// 이전버튼 클릭 이벤트
	$(document).on('click', 'a.prev' , function() {
		//alert(parseInt($('a.pnum').first().text().trim()) -1); 
		currentPage = parseInt($('a.pnum').first().text().trim()) -1;
		location.href="<%= request.getContextPath() %>/lboard/lBoardProList.do?pro_id=<%= Loginpro.getPro_id() %>&spage=" + currentPage;
	})
	
	
})

</script>
	
	<div class="container-content container mt-3">
		<div class="row">
			<div class="row col-md-12">
			<div class="frame content">
			<div class="stumanage">
				<div class="title stuttl">강의게시판</div>
				<div class="btngroup">
				<input type="button" class="btn btn-primary btn-sm" id="lbAddBtn" style="margin-bottom: 10px;"
				value="글쓰기" data-bs-toggle="modal" data-bs-target="#lbModal">
				</div>
				<div id="list">
					<table border="1"  class="table" id="lboard">
						<thead class="table-success">
					       <tr>
					       <td>분류</td>
					       <td>No.</td>
					       <td>제목</td>
					       <td>작성자</td>
					       <td>작성날짜</td>
					       <td>조회수</td>
					       </tr>
						</thead>
						<%
						if(list == null) {
						%>
							<tr>
								<td colspan="5">게시글이 없습니다.</td>
							</tr>
						<%
						}else {
							for(Lect_BoardVO lbList : list) {
						%>
							<tr>
								<td><%= lbList.getLb_nm() %></td>
								<td><a href="<%= request.getContextPath() %>/lboard/lBoardProDetail.do?lboard_no=<%= lbList.getLboard_no() %>">
									<%= lbList.getLboard_no() %></a></td>
								<td><a href="<%= request.getContextPath() %>/lboard/lBoardProDetail.do?lboard_no=<%= lbList.getLboard_no() %>">
									<%= lbList.getLboard_ttl() %></a></td>
								<td><%= lbList.getPro_nm() %></td>
								<td><%= lbList.getLboard_date().substring(0,10) %></td>
								<td><%= lbList.getLboard_rc() %></td>
							</tr>				
						<%
							}
						}
						%>
				    </table>
				    
				    
				    
	<div id="pagging">
	      <ul class="pagination">
	<%
		if(pagevo.getStartPage() > 1){
			// StartPage() 가 1보다 크다면 Previous출력
	%>
			  <li class="page-item"><a class="page-link prev" href="#">Previous</a></li>
<!-- 		  </ul> -->
		<%
			}
		%>
		
		
		<%	// 현재 페이지가 총 페이지보다 크다면 현재페이지는 총페이지 이다.
			if( currentPage > pagevo.getTotalPage()) currentPage = pagevo.getTotalPage();
		%> 
<!-- 			 <ul class="pagination"> -->
		
		<%	
			for(int i = pagevo.getStartPage(); i <= pagevo.getEndPage(); i++){
				if(i == currentPage){
					out.print("<li class='page-item active'><a class='page-link pnum' href='#'>" + i + "</a></li>");
				} else{
					out.print("<li class='page-item'><a class='page-link pnum' href='#'>" + i + "</a></li>");
				}
			}
		%>
<!-- 		</ul> -->
		
		<%	// 다음 출력
			if(pagevo.getEndPage() < pagevo.getTotalPage()){
				// out.print("<ul class='pagination'>");
				out.print("<li class='page-item'><a class='page-link next' href='#'>Next</a></li>");
			}
		%>
		</ul>
	</div>
					
					
					
					
				</div>
				<br>
			</div>
			</div>
			</div>
		</div>
	</div>

	<!-- The Modal -->
	<div class="modal" id="lbModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">게시글 작성</h4>
	        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body">
	        <form id="LBform" method="post" enctype="multipart/form-data" onsubmit="return false">
        	<label>분    류</label>
        	<select id="selectgu" name="lb_gu">
        	<%
        		for(LB_CodeVO clist : codeList){
        			out.print("<option value='" + clist.getLb_gu() + "'>" + clist.getLb_nm() + "</option>");
        		}
        	%>
        	</select><br>
        	
        	<label>제    목</label>
        	<input type="text" name="lboard_ttl" class="txt"><br>
        	
        	<label>작 성 자</label>
			<input type="hidden" name="pro_id" class="txt" value="<%= Loginpro.getPro_id() %>">    
			<input type="text" name="pro_nm" class="txt" value="<%= Loginpro.getPro_nm() %>" disabled><br>
        	
        	<label>본    문</label><br>
        	<textarea rows="10" cols="50" name="lboard_cn" class="txt"></textarea><br>
        	
        	<input type="file" name="lfile_nm1">
        	<br><br>
        	
        	<input type="button"  class="btn btn-primary btn-sm lbinsert" value="등록" id="wsend">
        	</form>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div>	
