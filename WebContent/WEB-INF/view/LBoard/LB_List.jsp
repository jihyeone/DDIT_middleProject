<%@page import="kr.or.ddit.middle.vo.PageVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.LB_CodeVO"%>
<%@page import="kr.or.ddit.middle.vo.Lect_BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// 서블릿에서 받은 자료
	List<Lect_BoardVO> list = (List<Lect_BoardVO>) request.getAttribute("list");
	List<LB_CodeVO> codeList = (List<LB_CodeVO>) request.getAttribute("codeList");
	List<ProfessorVO> proList = (List<ProfessorVO>) request.getAttribute("proList");
	
	PageVO pagevo = (PageVO) request.getAttribute("pagevo");
    int currentPage = (int) request.getAttribute("currentPage");
	
// 	ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
%>

<style type="text/css">
.form {
	margin: 0 auto;
}
.td {
 	padding: 20px;
}
table {
	text-align: center;
}
#stype {
	width: 70%;
}
label {
	display: inline-block;
	width: 70px;
}
#wform {
	margin-left: 30px;
}
#lbAddBtn {
	display: none;
}
#pagging {
	width: 200px;
	margin-left: 45%;
}
</style>


<script>
$(function() {
	// 페이징 번호 클릭시 다음페이지
	$(document).on('click','.pagination a.pnum', function(){
		currentPage = $(this).text().trim();
		location.href="<%=request.getContextPath() %>/lboard/lBoardList.do?spage=" + currentPage;
	})
	
 	// 다음 버튼 클릭 이벤트
	$(document).on('click', 'a.next' , function() {
	//alert(parseInt($('a.pnum').last().text().trim()) +1); 
		currentPage = parseInt($('a.pnum').last().text().trim()) + 1;
		location.href="<%=request.getContextPath() %>/lboard/lBoardList.do?spage=" + currentPage;
	})
	
	// 이전버튼 클릭 이벤트
	$(document).on('click', 'a.prev' , function() {
		//alert(parseInt($('a.pnum').first().text().trim()) -1); 
		currentPage = parseInt($('a.pnum').first().text().trim()) -1;
		location.href="<%=request.getContextPath() %>/lboard/lBoardList.do?spage=" + currentPage;
	})
	
})

</script>

	<div class="container-content container mt-3">
		<div class="row">
			<div class="row col-md-12">
			<div class="frame content">
			<div class="stumanage">
				<div class="title stuttl">강의게시판</div>
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
						if(list == null || list.size() ==0) {
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
								<td><a href="<%= request.getContextPath() %>/lboard/lBoardDetail.do?lboard_no=<%= lbList.getLboard_no() %>">
									<%= lbList.getLboard_no() %></a></td>
								<td><a href="<%= request.getContextPath() %>/lboard/lBoardDetail.do?lboard_no=<%= lbList.getLboard_no() %>">
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

