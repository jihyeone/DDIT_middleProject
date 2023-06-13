<%@page import="kr.or.ddit.middle.vo.PageVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%
	//서블릿에서 받은 자료
	List<CounselVO> clist = (List<CounselVO>) request.getAttribute("clist");
	ProfessorVO pvo = (ProfessorVO) request.getAttribute("pvo");
	
	//------페이지
	PageVO pagevo = (PageVO) request.getAttribute("pagevo");
	int currentPage = (int) request.getAttribute("currentPage");
	
	ProfessorVO Loginpro = (ProfessorVO) session.getAttribute("LoginProfessor");
	
	System.out.println("리스트 정보 = "+ clist);
	System.out.println("페이지 정보=" + pagevo);
	
	
%> 

<script type="text/javascript">
$(function() {
	// 페이징 번호 클릭시 다음페이지
	$(document).on('click','.pagination a.pnum', function(){
		currentPage = $(this).text().trim();
		location.href="<%= request.getContextPath()%>/counsel/CounselListPro.do?proId=<%= Loginpro.getPro_id() %>&spage=" + currentPage;
	})
	 	// 다음 버튼 클릭 이벤트
	$(document).on('click', 'a.next' , function() {
	//alert(parseInt($('a.pnum').last().text().trim()) +1); 
		currentPage = parseInt($('a.pnum').last().text().trim()) + 1;
		location.href="<%=request.getContextPath() %>/board/CommBoard.do?spage=" + currentPage;
	})
	
	// 이전버튼 클릭 이벤트
	$(document).on('click', 'a.prev' , function() {
		//alert(parseInt($('a.pnum').first().text().trim()) -1); 
		currentPage = parseInt($('a.pnum').first().text().trim()) -1;
		location.href="<%=request.getContextPath() %>/board/CommBoard.do?spage=" + currentPage;
	})
	
})


</script>

<style>
.form{
	margin: 0 auto;
}
.td{
	padding: 20px;
}
</style>

    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">상담예약</div>
	    	 		  <div class="btngroup">
	    	 		    <input type="button"  style="display: none;" class="btn btn-primary btn-sm counsleBtn" value="상담예약"
	    	 		    data-bs-toggle="modal" data-bs-target="#cnsModal">
	    	 		    </div>
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
        <tr>
        <td>작성자</td>
        <td>학급</td>
        <td style="width: 800px;">제목</td>
        <td>작성날짜</td>
        <td>답변확인</td>
        </tr>
	</thead>


<%
		if(clist == null || clist.size() == 0){
%>
	
   		<tr>
   			<td colspan="3">상담 요청 목록이 없습니다.</td>
   		</tr>
        
<%
		} else{
			
			for(CounselVO cnslist : clist){
				String check = "";
				
				if(cnslist.getCheckyn().equals("Y")){
					check = "답변완료";
				} else{
					check = "미확인";
				}
%>

        <tr>
        <td><%= cnslist.getStu_nm() %></td>
        <td><%= cnslist.getClass_no() %></td>
        <td><a href="<%= request.getContextPath()%>/counsel/CncDetailpro.do?cnsNo=<%=cnslist.getCns_no()%>">
        	<%= cnslist.getCns_ttl() %></a></td>
        
        <td><%= cnslist.getReg_date().substring(0,10) %></td>
        <td><%= check %></td>
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
				out.print(" <li class='page-item'><a class='page-link next' href='#'>Next</a></li>");
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
  
  
  