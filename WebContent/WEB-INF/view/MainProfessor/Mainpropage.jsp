<%@page import="kr.or.ddit.middle.vo.Lect_BoardVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="kr.or.ddit.middle.vo.CounselVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%
	// 강의게시판
	List<Lect_BoardVO> lBoardList = (List<Lect_BoardVO>) request.getAttribute("lBoardList");
	
	// 상담게시판
	List<CounselVO> recentCnsList = (List<CounselVO>) request.getAttribute("recentCnsList");
	
	// 가장 최신의 상담 목록
	CounselVO CnsRecent = (CounselVO) request.getAttribute("CnsRecent");
	// 최근 상담 학생 목록
	StudentVO CnsRecnetStu = (StudentVO) request.getAttribute("CnsRecnetStu");
	
	String sendMgsNM = ""; // 보낸사람 이름
	String sendMgsttl = "";
	
	System.out.println("받은 상담 메세지 = " + CnsRecent);
	System.out.println("메세지 보낸학생 = " + CnsRecnetStu);

%>



<script>
$(function() {
	
	$('.card-body').on('click', function() {
		location.href="<%=request.getContextPath() %>/counsel/CncDetailpro.do?cnsNo=<%=CnsRecent.getCns_no() %>" ;
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
                            	if(CnsRecent.getCns_ttl() != "null"){ // 상담목록이 있다면
                            		sendMgsNM = CnsRecent.getStu_nm();
                            		if(CnsRecent.getCns_ttl().length() > 7){
                            			sendMgsttl = CnsRecent.getCns_ttl().substring(0,7) + "...";
                            		} else{
                            			sendMgsttl = CnsRecent.getCns_ttl();
                            		}
                            
                            %>
                            
                                <table>
                                    <tr>
                                        <td rowspan="2"><img src="<%=request.getContextPath() %>/Info/StuImageView.do?stuId=<%= CnsRecent.getStu_id() %>" id="msgimg"></td>
                                        <td id="massageid"> 최근에 받은 상담요청<br>
                                         &nbsp;&nbsp;<%= sendMgsNM %></td> 
                                         
                                        <% if(CnsRecent.getCheckyn().equals("N")){ %>
                                          
                                        <td><span class="badge rounded-circle bg-danger" id="new" style="text-align: center;">N</span></td>
                                    
                                    	<%
                                        	}else{
                                        		out.print("<td style='width : 50px;'>승인</td>");
                                        	}
                                        %>
                                    
                                    </tr>
                                    <tr><td id="messageconent">&nbsp;&nbsp;<%= sendMgsttl %></td> </tr>
                              </table>
                              <%
	                            	} else{
	                        			out.print("<p style='padding : 20px;'>받은 상담요청 메세지가 없습니다.</p>");
	                        		}
	                              
                              %>
                              
                            </div>
                        </div>
                        <input type="button" class="btn btn-primary btn-lg" id="meetingroom" value="회의실 예약" style="display: none;">
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="row col-md-6">
                
                <div class="frame calender">
                    <div class="title calttl"><p class="titl-name">캘린더</p></div>
                        <iframe id="calenderifram" style="width: 500px; height: 500px;" src="<%=request.getContextPath() %>/layout/calernderSample.jsp"></iframe>   
                </div>
            </div>
            <div class="row col-md-6">
                <div class="frame board">

                    <div class="frame board1">
                        <div class="title Bttl"><p class="titl-name">강의게시판</p></div>
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
                      		if(lBoardList == null || lBoardList.size() ==0){
                      			out.print("<tr><td colspan='4'>등록된 게시글이 없습니다.</td></tr>");
                      		} else{
                      			
                      			for(Lect_BoardVO Llist : lBoardList){
                      	%>
                            <tr>
                                <td><%= Llist.getLboard_no() %></td>
                                <td><%= Llist.getLboard_ttl() %></td> 
                                <td><%= Llist.getPro_nm() %></td> 
                                <td><%= Llist.getLboard_rc() %></td> 
                            </tr>
                        <%
                      			}
                      		}
                        %>    
                        </tbody>
                        </table>
                    </div>
                    <div class="frame board2">
                        <div class="title Bttl"><p class="titl-name">상담게시판</p></div>
                        <table class="table"  id="Lboard">
                                <thead class="table-success">
                            <tr>
                                <td>작성자</td>
                                <td style="width: 200px;">제목</td> 
                                <td>작성날짜</td> 
                                <td>답변확인</td> 
                            </tr>
                            </thead>
                            <tbody>
                            
                                  
                            <%
                            	if(recentCnsList == null || recentCnsList.size() == 0){
                            		out.print("<tr><td colspan='4'>등록된 상담요청이 없습니다.</td></tr>");
                            	} else{
                            		
                            		for(CounselVO cnsList : recentCnsList ){
                            			
                            			String check ="";
                            			
                            			if(cnsList.getCheckyn().equals("Y")){
                            				check ="답변완료";
                            			} else{
                            				check ="미확인";
                            			}
                            		
                            %>
	                            <tr>
	                                <td><%= cnsList.getStu_nm()  %></td>
	                                <td><%= cnsList.getCns_ttl() %></td> 
	                                <td><%= cnsList.getReg_date().substring(0,10) %></td> 
	                                <td><%= check %></td> 
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
