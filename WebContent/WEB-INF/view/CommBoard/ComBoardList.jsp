<%@page import="kr.or.ddit.middle.vo.PageVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.Comm_CodeVO"%>
<%@page import="kr.or.ddit.middle.vo.Comm_BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%

   List<Comm_BoardVO> list = (List<Comm_BoardVO>) request.getAttribute("comBoardList");
   
   //글종류
   List<Comm_CodeVO> codevo =  (List<Comm_CodeVO>) request.getAttribute("codevo");
   
   PageVO pagevo = (PageVO) request.getAttribute("pagevo");
   int currentPage = (int) request.getAttribute("currentPage");
   
    System.out.println("리스트 정보 = "+ list);
	System.out.println("페이지 정보=" + pagevo);
	System.out.println("현재페이지 정보=" + currentPage);
	System.out.println("스타트페이지 정보=" + pagevo.getStartPage());
	
   
   
   StudentVO stuvo = (StudentVO) session.getAttribute("LoginStudent");
%>
<script type="text/javascript">
$(function(){
   $('.insertCom').on('click', function(){
      vcomform = $('#ComForm').serialize();
      $.ajax({
         url : "<%=request.getContextPath() %>/board/ComBoardInsert.do" ,
         data : vcomform ,
         type : 'post',
         success : function(res) {
            if(res.flag != "실패"){
            alert('글이 등록되었습니다.')
            location.href="<%= request.getContextPath()%>/board/CommBoard.do?spage=1"
            }
         },
         error : function(xhr){
            alert("오류 : " + xhr.status);
         },
         dataType : 'json'
      })
   })

//---------------------------------------------------------------------------------------
  $('#search').on('click', function(){
	  
	stype = $('#stype option:selected').val();
	sword = $('#sword').val().trim();
	
	console.log("stype = " + stype, "sword =" + sword)
	
   
	location.href="<%=request.getContextPath() %>/board/CommBoard.do?spage=1" 
			+ "&stype=" + stype + "&sword=" + sword ;
	
  })


//---------------------------------------------------------------------------------------
 
	// 페이징 번호 클릭시 다음페이지
	$(document).on('click','.pagination a.pnum', function(){
		currentPage = $(this).text().trim();
		location.href="<%=request.getContextPath() %>/board/CommBoard.do?spage=" + currentPage;
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
.form-select, .form-control{
	display: inline;
}

#pagging {
	width: 200px;
}
.stumanage {
	margin-top: 30px;
}
</style>

    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
                 <div class="title stuttl">커뮤니티 게시판</div>
                   <div class="btngroup" style="margin-bottom: 10px;">
                  
<!--                     <select id="board_cat_select" name="board_cat_no" > -->
<!--                                <option value="0" selected>전체</option> -->
<!--                                <option value="1">잡담</option> -->
<!--                                 <option value="2" >질문</option> -->
<!--                        </select> -->
                       <select id="stype" class="form-select" style="width: 100px;">
                          <option value="">전체</option>
                          <option value="stu_nm">이름</option>
                           <option value="comm_ttl">제목</option>
                           <option value="comm_cn">내용</option>
                     </select>    
                        
                    <input id="sword" type="text" placeholder="Search" class="form-control"  style="width: 200px;">
                       <button id="search" class="btn btn-primary btn-sm searBtn" type="button">Search</button>
              
           
              
              <% if(stuvo != null){ %>
                     <input type="button" class="btn btn-primary btn-sm comBtn" value="글쓰기"
                     data-bs-toggle="modal" data-bs-target="#ComModal">
              <% }%>    
                     </div>
                        
                        <div id="list">
 <table border="1"  class="table" id="Cboard">
    <thead class="table-success">
        <tr>
        <td>No</td>
        <td style="width: 800px;">제목</td>
        <td>작성자</td>
        <td>작성날짜</td>
        <td>글종류</td>
        <td>조회수</td>
        </tr>
   </thead>

<%
      if(list.size() == 0 || list == null){
%>
   
         <tr>
            <td colspan="6">커뮤니티 게시판에 작성된 글이 없습니다.</td>
         </tr>
        
<%
      } else{
         
         for(Comm_BoardVO clist : list){
            
%>

        <tr>
        <td><a href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%=clist.getComm_no()%>">
           <%= clist.getComm_no() %></a></td>
        <td><a href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%=clist.getComm_no()%>">
           <%= clist.getComm_ttl() %></a></td>
        <td><%= clist.getStu_nm() %></td> 
        <td><%= clist.getComm_date().substring(0,10) %></td>
        <td><%= clist.getComm_nm() %></td>       
        <td><%= clist.getComm_rc()%></td>
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
<div class="modal" id="ComModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">새 글쓰기</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="ComForm">
           <table> 
              <tr><td>글제목<td>
                 <td><input type="text" name="com_ttl" style="width: 300px;"><td>
              </tr>
              <tr><td>글종류 선택<td>
                 <td><select name="selectgu">
                 <%
                    for(Comm_CodeVO clist : codevo){
                       out.print("<option value='"+ clist.getComm_gu() +"'>" + clist.getComm_nm() + "</option>") ;
                    }
                 %>
                 </select></td>
              </tr>
              <tr><td>등록내용</td>
                 <td></td>
              </tr>
           </table>
           <textarea name="com_cn" style="height: 200px; width: 370px;"></textarea><br>
           <input type="button" value="등록" class="btn btn-primary btn-sm insertCom">
           <input type="reset" value="취소" class="btn btn-primary btn-sm cancelCom">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">닫기</button>
      </div>

    </div>
  </div>
</div>