<%@page import="kr.or.ddit.middle.vo.PageVO"%>
<%@page import="kr.or.ddit.middle.vo.RoomVO"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="/layout/common_lib.jsp" flush="false" />
 <!DOCTYPE html>
    <html>
    <head>
	<body>
 <%
 	List<RoomVO> rlist = (List<RoomVO>)request.getAttribute("roomList");
 
	PageVO pagevo = (PageVO) request.getAttribute("pagevo");
	int currentPage = (int) request.getAttribute("currentPage");
	String rmtype = (String) request.getAttribute("rm_type");
	if(rmtype == null){
		rmtype = "";
	}
 
 %>   
<script type="text/javascript">
sval = "";
	function proc1() {
		sval = $('#rm_type').val();
		console.log(sval);
		location.href="<%=request.getContextPath()%>/RoomList.do?spage=1&rm_type="+sval;
	}
	$(function() {
		$('#rm_type').val("<%= rmtype%>");
		
		$(".UpdateModal").on("click", function(e){
			e.preventDefault();
			$("#rm_no").val($(this).data("rm_no"));
			$("#rmnoSpan").text($(this).data("rm_no"));
			$("#Updatetype").val($(this).data("rm_type"));
			$("#UpdateRoom").val($(this).data("useyn"));
		});
		
		$('.SaveRoom').on('click',function(){
			$.ajax({
				url : '<%=request.getContextPath()%>/UpdateRoom.do',
				data: {"rm_no" : $("#rm_no").val(),
					"rm_type" : $("#Updatetype").val(),
					"useyn" : $("#UpdateRoom").val() },
					type :'post',
					success : function(res){
						if(res.flag=="성공"){
							alert("수정이 완료되었습니다.");
							location.href="<%=request.getContextPath()%>/RoomList.do?spage=1";
						}
					},
					error : function(xhr){
						alert("오류"+xhr.status);
					},
					dataType : 'json'
			});
			
		})
		
	// 페이징 번호 클릭시 다음페이지
	$(document).on('click','.pagination a.pnum', function(){
		sval = $('#rm_type').val();
		currentPage = $(this).text().trim();
		location.href="<%=request.getContextPath()%>/RoomList.do?spage=" + currentPage + "&rm_type=" + sval;
	})
	 	// 다음 버튼 클릭 이벤트
	$(document).on('click', 'a.next' , function() {
	//alert(parseInt($('a.pnum').last().text().trim()) +1); 
		sval = $('#rm_type').val();
		currentPage = parseInt($('a.pnum').last().text().trim()) + 1;
		location.href=href="<%=request.getContextPath()%>/RoomList.do?spage=" + currentPage  + "&rm_type=" + sval;
	})
	
	// 이전버튼 클릭 이벤트
	$(document).on('click', 'a.prev' , function() {
		//alert(parseInt($('a.pnum').first().text().trim()) -1); 
		sval = $('#rm_type').val();
		currentPage = parseInt($('a.pnum').first().text().trim()) -1;
		location.href=href="<%=request.getContextPath()%>/RoomList.do?spage=" + currentPage  + "&rm_type=" + sval;
	})
		
		
		
		
		
	})
		
</script>
<style>
select {
	margin: 5px;
}

.roommodify {
	float: right;
	margin-right: 5px;
	margin-left: 5px;
	margin-top: 8px;
}

.form-select {
	display: inline;
}

#pagging {
	width: 200px;
}
</style>
    
    
   
     <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
                        <div class="title stuttl">교실관리</div>
                        <div class="btngroup" style="margin-bottom: 10px;">
                            <input type="button" class="btn btn-primary btn-sm roommodify" value="수정">
                            <input type="button" class="btn btn-primary btn-sm roommodify" value="검색" onclick="proc1()">
                        
                        <select id = "rm_type" class="form-select" style="width: 100px;">
                        	<option value="" >전체</option>
                        	<option value="강의실">강의실</option>
                        	<option value="창고">창고</option>
                        	<option value="비품실">비품실</option>
                        	<option value="이사장실">이사장실</option>
                        	<option value="교무실">교무실</option>
                        	<option value="회의실">회의실</option>
                        	<option value="휴게실">휴게실</option>
                        	<option value="세미나실">세미나실</option>
                       	</select>
                        </div>
                        <div id="list">
                        <table class="table"  id="Cboard">
                            <thead class="table-success">
                                <tr>
                                    <td>강의실번호</td>
                                    <td>사용용도</td>
                                    <td>사용여부</td>
                                </tr>
                            </thead>
                           <%
	if(rlist ==null || rlist.size() == 0){
%>                            
   <tr>
   	<td colspan="3">강의실 목록이 없습니다..</td>
   </tr>
   <%
	}else{
		
		for(RoomVO rmlist : rlist){
   %>            
                                <tr>
                                    <td><a data-bs-toggle="modal" data-bs-target="#UpdateModal" 
                                    data-rm_no="<%=rmlist.getRm_no()%>"  
                                    data-rm_type="<%=rmlist.getRm_type() %>" 
                                    data-useyn="<%=rmlist.getUseyn() %>"
                                    class="UpdateModal" href="#"><%=rmlist.getRm_no()%></a></td>
                                    <td data-rm_type="<%=rmlist.getRm_type() %>"> <%=rmlist.getRm_type() %></td>
                                    <td data-useyn="<%=rmlist.getUseyn() %>"><%=rmlist.getUseyn() %></td>
                                </tr>
<%
		}
	}
%>	   
                    </tbody>
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
                    </div>
                </div>
    	</div> 
    	
    	<!-- The Modal -->
<div class="modal" id="UpdateModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">교실변경</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="CnsOKForm">
        	<input type="hidden" name="" id="rm_no">
        	<label>강의실번호 : </label><span id="rmnoSpan"></span><br>
       		<label>사용용도 : </label>
       		<select id="Updatetype">
                        	<option value="강의실">강의실</option>
                        	<option value="창고">창고</option>
                        	<option value="비품실">비품실</option>
                        	<option value="이사장실">이사장실</option>
                        	<option value="교무실">교무실</option>
                        	<option value="회의실">회의실</option>
                        	<option value="휴게실">휴게실</option>
                        	<option value="세미나실">세미나실</option>
                       	</select>
       		<label>사용여부 :
       			<select id="UpdateRoom">
       				<option value="Y">Y</option>
       				<option value="N">N</option>
       			</select><br>
       		</label>
        	<input type="button" value="저장" class="btn btn-primary btn SaveRoom">
        	<input type="reset" value="취소" class="btn btn-primary btn cancelCns">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary close" data-bs-dismiss="modal" >닫기</button>
      </div>

    </div>
  </div>
</div>
  
    	
       
          </head>
          </html>