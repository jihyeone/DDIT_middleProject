<%@page import="kr.or.ddit.middle.vo.Lect_BoardVO"%>
<%@page import="kr.or.ddit.middle.vo.Comm_BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.MessageVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>





<%

	// 가져와야 할 것
	// 강의게시판
	List<Lect_BoardVO> lBoardList = (List<Lect_BoardVO>) request.getAttribute("lBoardList");
	
	// 커뮤니티게시판
	List<Comm_BoardVO> cBoardList = (List<Comm_BoardVO>) request.getAttribute("cBoardList");
	
	// 학생이 받은 최신 메세지
	MessageVO mgs =  (MessageVO) request.getAttribute("stuMessage");
	StudentVO sendMgsstuInfo = (StudentVO) request.getAttribute("sendMgsStuInfo");
	String sendMgsNM = ""; // 보낸사람 이름
	String sendMgsttl = "";
	
	System.out.println("받은 메세지 = " + mgs);
	System.out.println("메세지 보낸학생 = " + sendMgsstuInfo);
	
	

%>

<script>
$(function() {
// 	$('.schedule').on('click', function() {
// 		alert('확인')
// 	})
	
	
	// 회의실 새창 열기
	$('#meetingroom').on('click', function() {
		var openNewWindow = window.open("about:blank");
		openNewWindow.location.href = "https://www.ddit.or.kr/room";
	})
	$('.card-body').on('click', function() {
		location.href="<%=request.getContextPath() %>/messageRecView.do?msg_no=<%=mgs.getMsg_no() %>" ;
	})
})
</script>


    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="magbody">
                    <div class="frame message">
                        <div class="card">
                            <div class="card-body">
                            
                            
                            <%
                            	if(mgs.getMsg_ttl() != "null"){ // 받은 메세지가 있다면
                            		sendMgsNM = mgs.getSend_nm();
                            		if(mgs.getMsg_ttl().length() > 7){
                            			sendMgsttl = mgs.getMsg_ttl().substring(0,6) + "...";
                            		} else{
                            			sendMgsttl = mgs.getMsg_ttl();
                            		}
                            		
                            %>
                                <table id="msgtable">
                                    <tr>
                                        <td rowspan="2"><img src="<%=request.getContextPath() %>/Info/StuImageView.do?stuId=<%= sendMgsstuInfo.getStu_id() %>" id="msgimg"></td>
                                        <td id="massageid">최근에 받은 쪽지<br>
                                         &nbsp;&nbsp;<%= sendMgsNM %></td> 
                                        
                                        <% if(mgs.getReadyn().equals("N")){ %>
                                        
                                        <td><span class="badge rounded-circle bg-danger" id="new" style="text-align: center;">N</span></td>
                                        
                                        <%
                                        	}else{
                                        		out.print("<td style='width : 50px;'>읽음</td>");
                                        	}
                                        %>
                                    </tr>
                                    <tr><td id="messageconent">&nbsp;&nbsp;<%= sendMgsttl %></td> </tr>
                              </table>
                              
                              <%
                            		} else{
                            			out.print("<p style='padding : 20px;'>받은 메세지가 없습니다.</p>");
                            		}
                              %>
                              
                            </div>
                        </div>
                        <input type="button" class="btn btn-primary btn-lg" id="meetingroom" value="회의실 예약">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="row col-md-6">
                
                <div class="frame calender">
                    <div class="title calttl schedule"><p class="titl-name">캘린더</p></div>
                        <iframe id="calenderifram" style="width: 500px; height: 500px;" src="<%=request.getContextPath() %>/layout/calernderSample.jsp"></iframe>   
                </div>
            </div>
            <div class="row col-md-6">
                <div class="frame board">

                    <div class="frame board1">
                        <div class="title Bttl"><p class="titl-name">커뮤니티게시판</p></div>
                        <table class="table"  id="Cboard">
                                <thead class="table-success">
                            <tr>
                                <td>번호</td>
                                <td style="width: 200px;">제목</td> 
                                <td>작성자</td> 
                                <td>조회수</td> 
                            </tr>
                            </thead>
                            <tbody>
                            <%
                            	if(cBoardList == null || cBoardList.size() == 0){
                            		out.print("<tr><td colspan='4'>현재 등록된 게시글이 없습니다.</td></tr>");
                            	} else{
                            		for(Comm_BoardVO cbList : cBoardList){
                            %>
                            <tr>
                            	<td><%= cbList.getComm_no()%></td>
                                <td><a href="<%= request.getContextPath()%>/board/CommBoardView.do?cNo=<%= cbList.getComm_no()%>">
                                <%= cbList.getComm_ttl() %></a></td> 
                                <td><%= cbList.getStu_nm() %></td> 
                                <td><%= cbList.getComm_rc() %></td> 
                            </tr>
                            <%
                            		}
                            
                            	}
                            %>
                        </tbody>
                        </table>
                    </div>
                    <div class="frame board2">
                        <div class="title Bttl"><p class="titl-name">강의게시판</p></div>
                        <table class="table"  id="Lboard">
                                <thead class="table-success">
                            <tr>
                                <td>번호</td>
                                <td style="width: 200px;">제목</td> 
                                <td>작성자</td> 
                                <td>조회수</td> 
                            </tr>
                            </thead>
                            <tbody>
                            <%
                            	if(lBoardList == null || lBoardList.size() == 0){
                            		out.print("<tr><td colspan='4'>현재 등록된 게시글이 없습니다.</td></tr>");
                            	} else{
                            		
                            		for(Lect_BoardVO lList : lBoardList){
                            %>
                            	<tr>
                            		<td><%= lList.getLboard_no()%></td>
                            		<td><a href="<%= request.getContextPath() %>/lboard/lBoardDetail.do?lboard_no=<%= lList.getLboard_no() %>">
                            		<%= lList.getLboard_ttl()%></a></td>
                            		<td><%= lList.getPro_nm()%></td>
                            		<td><%= lList.getLboard_rc()%></td>
                            	</tr>
                            
                            
                            
                            <%
                            		}
                            	}
                            %>
                        </tbody>
                        </table>
                    </div>
                </div>
            </div>
                </div>
            </div>
       