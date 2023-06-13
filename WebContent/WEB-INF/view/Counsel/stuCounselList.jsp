<%@page import="kr.or.ddit.middle.vo.PageVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.ProfessorVO"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%

	// 서블릿에서 받은 자료 CounselList.java = 학생 자신이 쓴 상담요청 리스트
	
	List<CounselVO> list = (List<CounselVO>) request.getAttribute("stucounselList");
	List<ProfessorVO> prolist = (List<ProfessorVO>) request.getAttribute("proList");
	StudentVO Loginstu = (StudentVO) session.getAttribute("LoginStudent");
	System.out.println("상담목록 = " + list);
	
	//------페이지
	PageVO pagevo = (PageVO) request.getAttribute("pagevo");
	int currentPage = (int) request.getAttribute("currentPage");
	
	
%> 

<script type="text/javascript">
$(function() {
	
	$('.insertCns').on('click', function() {
		
		vcnsform = $('#CnsForm').serialize();
		
		vcnsform += "&stu_id=" + "<%= Loginstu.getStu_id()%>" + "&pro_id=" + $('#selectpro option:selected').val().trim();
		$.ajax({							  
			url : '<%=request.getContextPath() %>/counsel/ConselInsert.do',
			data : vcnsform ,
			type : 'post',
			success : function(res){
				alert("상담예약 신청이 등록되었습니다.");
				location.href="<%=request.getContextPath() %>/counsel/CounselListstu.do?stuId=<%= Loginstu.getStu_id()%>&spage=1";
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	
		// 페이징 번호 클릭시 다음페이지
	$(document).on('click','.pagination a.pnum', function(){
		currentPage = $(this).text().trim();
		location.href=href="<%= request.getContextPath() %>/counsel/CounselListstu.do?stuId=<%= Loginstu.getStu_id() %>&spage=" + currentPage;
	})
	 	// 다음 버튼 클릭 이벤트
	$(document).on('click', 'a.next' , function() {
	//alert(parseInt($('a.pnum').last().text().trim()) +1); 
		currentPage = parseInt($('a.pnum').last().text().trim()) + 1;
		location.href=href="<%= request.getContextPath() %>/counsel/CounselListstu.do?stuId=<%= Loginstu.getStu_id() %>&spage=" + currentPage;
	})
	
	// 이전버튼 클릭 이벤트
	$(document).on('click', 'a.prev' , function() {
		//alert(parseInt($('a.pnum').first().text().trim()) -1); 
		currentPage = parseInt($('a.pnum').first().text().trim()) -1;
		location.href=href="<%= request.getContextPath() %>/counsel/CounselListstu.do?stuId=<%= Loginstu.getStu_id() %>&spage=" + currentPage;
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
.counsleBtn{
	margin-right: 10px;
	margin-bottom: 10px;
}
</style>

    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">상담예약</div>
	    	 		  <div class="btngroup">
	    	 		    <input type="button" class="btn btn-primary btn-sm counsleBtn" value="상담예약"
	    	 		    data-bs-toggle="modal" data-bs-target="#cnsModal">
	    	 		    </div>
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
 	<thead class="table-success">
        <tr>
        <td>No</td>
        <td style="width: 800px;">제목</td>
        <td>작성날짜</td>
        <td>답변확인</td>
        </tr>
	</thead>
<%
		if(list == null || list.size() == 0){
%>
		<tr><td colspan='3'>상담 요청 목록이 없습니다.</td></tr>

<%
		} else{
			
			for(CounselVO clist : list){
				String check = "";
				
				if(clist.getCheckyn().equals("Y")){
					check = "답변완료";
				} else{
					check = "미확인";
				}
%>

        <tr>
        <td><a href="<%= request.getContextPath()%>/counsel/CncDetailstu.do?cnsNo=<%=clist.getCns_no()%>">
        	<%= clist.getCns_no() %></a></td>
        <td><a href="<%= request.getContextPath()%>/counsel/CncDetailstu.do?cnsNo=<%=clist.getCns_no()%>">
        	<%= clist.getCns_ttl() %></a></td>
        <td><%= clist.getReg_date().substring(0,10) %></td>
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
		
		<%	// 다음 출력
			if(pagevo.getEndPage() < pagevo.getTotalPage()){
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
<div class="modal" id="cnsModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">상담예약</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="CnsForm">
        	<table> 
        		<tr><td>상담제목<td>
        			<td><input type="text" name="cns_ttl" style="width: 300px;"><td>
        		</tr>
        		<tr><td>교수선택<td>
        			<td><select id="selectpro">
        			<%
        				for(ProfessorVO plist : prolist){
        					out.print("<option value='"+ plist.getPro_id() +"'>" + plist.getPro_nm() + "</option>") ;
        				}
        			%>
        			</select></td>
        		</tr>
        		<tr><td>상담내용</td>
        			<td></td>
        		</tr>
        	</table>
        	<textarea name="cns_cn" style="height: 200px; width: 370px;"></textarea><br>
        	<input type="button" value="제출" class="btn btn-primary btn-sm insertCns">
        	<input type="reset" value="취소" class="btn btn-primary btn-sm cancelCns">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
      </div>

    </div>
  </div>
</div>
  
    