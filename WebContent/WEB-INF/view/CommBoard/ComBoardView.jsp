
<%@page import="kr.or.ddit.middle.vo.Comm_CommentVO"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
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
	// 글 수정 -> 글종류
	List<Comm_CodeVO> codevo =  (List<Comm_CodeVO>) request.getAttribute("codevo");
	//세션에서 가져온 회원정보값
	StudentVO svo = (StudentVO) request.getAttribute("svo");
	//댓글 리스트
	List<Comm_CommentVO> replyList = (List<Comm_CommentVO>)request.getAttribute("replyList");
	
%>



<script>
$(function() {
	
	
	
	$('.ComList').on('click', function() {
		location.href="<%= request.getContextPath()%>/board/CommBoard.do?spage=1"
	});
	
	
	$('.updateCom').on('click', function(){
		vcomform = $('#ComForm').serialize();
		$.ajax({
			url : "<%=request.getContextPath() %>/board/comBoardUpdate.do?cNo=<%= cvo.getComm_no()%>" ,
			data : vcomform ,
			type : 'post',
			success : function(res) {
				if(res.flag != "실패"){
				alert('글이 수정되었습니다.');
				location.href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%= cvo.getComm_no()%>"
				}
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
	});
	
	

	$('.Delete').on('click', function() {

		
			$.ajax({
				url : '<%=request.getContextPath() %>/board/comBoardDelete.do?cNo=<%= cvo.getComm_no()%>',
				data : { "com_no" : "<%= cvo.getComm_no() %>" },
				type : 'post',
				success : function(res) {
					alert('글이 삭제되었습니다.')
					location.href="<%= request.getContextPath()%>/board/CommBoard.do?spage=1"
				},
				error : function(xhr){
					alert("오류 : " + xhr.status);
				},
				dataType : 'json'
			})
		
	});
	
	$('.reInsertBtn').on('click', function(){
		
		vcom_reInsert = $('#com_reply_insert').val();
		
		
		$.ajax({
			url : '<%=request.getContextPath() %>/reply/replyInsert.do',
			data : { "com_no" : "<%= cvo.getComm_no() %>",
				     "stu_id" : "<%= svo.getStu_id()%>",
				     "cmnt_cn" : vcom_reInsert } ,
				   
			type : 'post',
			success : function(res) {
				alert('댓글이 등록되었습니다.')
				location.href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%= cvo.getComm_no()%>"
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
	});
	
	
	$('.re_delete').on('click', function(){
		
		vrenum = $('#re_num').val();
		
		$.ajax({
			url : '<%=request.getContextPath() %>/reply/replyDelete.do',
			data : { "re_no" : vrenum },
			type : 'post',
			success : function(res) {
				alert('댓글이 삭제되었습니다.')
				location.href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%= cvo.getComm_no()%>"
			},
			error : function(xhr){
				alert("오류 : " + xhr.status);
			},
			dataType : 'json'
		})
		});
	
	
	
	
	 $(document).on("click","button[name='re_modify']", function(){
			$('.newcn').css('display', 'none');	
			$('.oldcn').css('display', 'block');

			$(this).closest(".table").find('.oldcn').css('display', 'none');
		    $(this).closest(".table").find('.newcn').css('display', 'block');	
		});
	
	
	
	 $(".re_update_insert").on("click", function(){
	        
			vupform = $(this.form).serialize();
			console.log(vupform);
			
			$.ajax({
				url : '<%=request.getContextPath() %>/reply/replyUpdate.do',
				data : vupform,
				type : 'post',
				success : function(res) {
					alert('댓글이 수정되었습니다.')
					location.href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%= cvo.getComm_no()%>"
				},
				error : function(xhr){
					alert("오류 : " + xhr.status);
				},
				dataType : 'json'
			
			})
		});
		

	
		
	
	
	
})






</script>

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
    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl"></div>
	    	 	
	    	 <% if(!svo.getStu_id().equals(cvo.getStu_id()) ) { %>	
	    	 		
  	 		  <div class="btngroup">
 	 		    <input type="button" class="btn btn-primary btn-sm ComList" value="목록으로 돌아가기">
 	 		    </div>
 	 		    
 	 		<%} else { %>
 			 <div class="btngroup">
 	 		    <input type="button" class="btn btn-primary btn-sm ComList" value="목록으로 돌아가기">
 	 		     <input type="button" class="btn btn-primary btn-sm Update"  value="수정"
	    	 		    data-bs-toggle="modal" data-bs-target="#comModal">
 	 		   	 <input type="button" class="btn btn-primary btn-sm Delete" value="삭제">
 	 		    </div>
 	 		<%} %>    
 	 	
 	 	<%
	if(cvo == null){ // 객체가 없으면

		out.println("<h4>해당 글 내역이 없습니다.</h4>");

	} else{ // 내역이 있으면
		

		
%>	
	<form>
	<label>제&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label> <span class="Cnsspan">&nbsp;<%= cvo.getComm_ttl() %></span><br>
	
	<label>작성날짜 : </label><span class="Cnsspan">&nbsp;<%= cvo.getComm_date().substring(0,10) %></span><br>
	<label>작 성 자&nbsp; : </label><span class="Cnsspan">&nbsp;<%= cvo.getStu_nm() %></span><br>
	<label>조 회 수&nbsp; : </label><span class="Cnsspan">&nbsp;<%= cvo.getComm_rc() %></span><br>
	<label>글 종 류&nbsp; : </label><span class="Cnsspan">&nbsp;<%= guvo.getComm_nm() %></span><br>
	<label>작성내용 : </label><hr>
	<p><%= cvo.getComm_cn() %></p>
	</form>
	
	<hr>
  		
  		<h6>댓글</h6>
		<td>댓글 작성자 : </label><a><%=svo.getStu_nm() %></td><br>
		
		<td>댓글내용</td>
		<td> 		
 	<form>
      <div class="container mt-3">
         <textarea rows="2"  class="txt"  cols="60" name="com_reply_insert" id="com_reply_insert"></textarea> 
         <input class="btn btn-primary btn-sm reInsertBtn" style= "width : 68px;height : 70px;border-bottom-width: 1px;margin-bottom: 40px;"type="button" value="등록">
      </div>
   </form>
 		</td>
	

	
	
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
								<form form class ="newcn" id = "newcn" name = "newcn" style = "display : none">
									<input type="hidden" id="up_re_num" name="up_re_num" value="<%=replyList.get(i).getCmnt_no()%>">
									<textarea rows="2" cols="60" name="com_reply_update" id="com_reply_update" placeholder="수정할 댓글을 작성하세요"><%= replyList.get(i).getCmnt_cn() %></textarea>
									<input type="button" class="re_update_insert" value="수정댓글 등록">
									<input type="reset" value="취소"></button>
								</form>
						
							
							
							
							
							
													<td align="right">
														<%
														if(replyList.get(i).getStu_id() != null && replyList.get(i).getStu_id().equals(svo.getStu_id())){   
														%>
															<form id = "re-button">
															 <input type="hidden" id="re_num" name="re_num" value="<%=replyList.get(i).getCmnt_no()%>">
															 <input type="hidden" id="re_cont" name="re_cont" value="<%=replyList.get(i).getCmnt_cn()%>">
															 <input type="hidden" id="re_id" name="re_id" value="<%=replyList.get(i).getStu_id()%>">
															 
																<button type="button" name="re_modify" id="re_modify" class="btn-primary">수정</a>
																<button type="button" id="re_delete" class="btn-primary re_delete">삭제</a>
															</form>																		
														<%
														}
														%>	
													</td>
												</div>
												
												
												
													
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
    


 
 <!-- The Modal -->
<div class="modal" id="comModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">글 수정</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="ComForm">
        	<table style ="height : 52px;"> 
        		<tr><td style= "height: 2px; width: 302px;">수정제목<td>
        			<td><input type="text" name="com_ttl" style="width: 300px;" value="<%=cvo.getComm_ttl() %>"><td>
        		</tr>
        		<tr style = "height : 50px;"><td>글 종류 선택<td>
        			<td><select id="selectgu" name="selectgu">
        			<%
        				for(Comm_CodeVO clist : codevo){
        					out.print("<option value='"+ clist.getComm_gu() +"'>" + clist.getComm_nm() + "</option>") ;
        				}
        			%>
        			</select></td>
        		</tr>
        		<tr style = "height : 200;"><td>수정내용</td>
        			<td></td>
        		</tr>
        	</table>
        	<textarea name="com_cn" style="height: 356px; width: 426px;"><%=cvo.getComm_cn() %></textarea><br>
        	<input type="button" value="수정" class="btn btn-primary btn-sm updateCom">
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