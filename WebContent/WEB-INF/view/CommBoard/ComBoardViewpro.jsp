<%@page import="kr.or.ddit.middle.vo.Comm_CommentVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.Comm_CodeVO"%>
<%@page import="kr.or.ddit.middle.vo.Comm_BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<%

	// 커뮤니티 글 세부사항 보기
	Comm_BoardVO cvo = (Comm_BoardVO)request.getAttribute("BoardView");
	// 커뮤니티 글종류 카테고리
	Comm_CodeVO guvo = (Comm_CodeVO) request.getAttribute("gu");
	//댓글 리스트
	List<Comm_CommentVO> replyList = (List<Comm_CommentVO>)request.getAttribute("replyList");
%>
<style>

 table{ 
 	margin: 0 auto; 
 	padding: 20px;
 }


 form{ 
 	margin : 10px; 
/*  	height : 500px;  */
 	padding: 10px; 
 } 

 label{ 
 	font-weight: bold; 
 	display: inline-block;
 	width: 100px; 
 } 
</style>

<script>
$(function() {
	$('.ComList').on('click', function() {
		location.href="<%= request.getContextPath()%>/board/CommBoard.do?spage=1";
	})
})

</script>

    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl"></div>
	    	 	
	    	 		
  	 		  <div class="btngroup">
 	 		    <input type="button" class="btn btn-primary btn-sm ComList" value="목록으로 돌아가기">
 	 		    </div>
 	 	
 	 	<%
	if(cvo == null ){ // 객체가 없으면

		out.println("<h4>해당 글 내역이 없습니다.</h4>");

	} else{ // 내역이 있으면
		

		
%>	
	<form>
	<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label> <span class="Cnsspan">&nbsp;<%= cvo.getComm_ttl() %></span><br>
	
	<label>작성날짜 : </label><span class="Cnsspan">&nbsp;<%= cvo.getComm_date().substring(0,10) %></span><br>
	<label>작 성 자 : </label><span class="Cnsspan">&nbsp;<%= cvo.getStu_nm() %></span><br>
	<label>조 회 수 : </label><span class="Cnsspan">&nbsp;<%= cvo.getComm_rc() %></span><br>
	<label>글 종 류 : </label><span class="Cnsspan">&nbsp;<%= guvo.getComm_nm() %></span><br>
	<label>작성내용 : </label><hr>
	<p><%= cvo.getComm_cn() %></p>
	</form>
	
	<hr>
  		
  		<h6>댓글</h6>
	
			<div class="container">
			<div class="row">
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<tbody>
						<%
							if(replyList.size() == 0 || replyList == null){
						%>
	
   							<tr>
   								<td>작성된 댓글이 없습니다.</td>
   							</tr>
        
						<%
							} else{
						%>			
						<tr>
						<%
						
							for(int i=0; i<replyList.size(); i++){
						%>
								<div class="container">
									<div class="row">
										<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
											<tbody>
												<tr>						
													<td align="left"><%= replyList.get(i).getStu_nm() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= replyList.get(i).getCmnt_date() %><br>		
													
						
					
					
					<!-- 댓글 수정창 -->		
					
						<div class="modyRe" >
							<p class= "oldcn" id ="oldcn"><%= replyList.get(i).getCmnt_cn() %></p>
								<form class ="newcn" id = "newcn" name = "newcn" style = "display : none">
									<input type="hidden" id="up_re_num" name="up_re_num" value="<%=replyList.get(i).getCmnt_no()%>">
									<textarea rows="2" cols="60" name="com_reply_update" id="com_reply_update" placeholder="수정할 댓글을 작성하세요"><%= replyList.get(i).getCmnt_cn() %></textarea>
									<input type="button" class="re_update_insert" value="수정댓글 등록">
									<input type="reset" value="취소"></button>
								</form>
						
												</div>
												
													</td>
												</tr>
												
											</tbody>
										</table>			
									</div>
								</div>			
						</tr>
						<%
							}
						%>
								
										
		
	
					<%
							}
						
					%> 

					</tbody>
				</table>
 			</div>	
			</div>    
	
<%
	}
%>
					</div>
	    	 	</div>
    	 	</div>
    	 </div>
    </div>