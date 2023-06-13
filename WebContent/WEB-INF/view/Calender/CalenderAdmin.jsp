<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.or.ddit.middle.vo.ClassClassVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.middle.vo.ScheduleVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	List<ScheduleVO> sublist = (List<ScheduleVO>) request.getAttribute("sublist");
	List<ClassClassVO> classList = (List<ClassClassVO>) request.getAttribute("classList");
	JSONArray jarr = (JSONArray) request.getAttribute("jsonArr");
	System.out.println("여기는 jsp에서 넘어온 값입니다.-----------------------");
	String setclass = (String) request.getAttribute("setclass");
	System.out.println("현재 설정된 class : "+ setclass +" => jarr =>" + jarr);
	
	if(setclass == null){
		setclass = "2211";
	}
	
	List<Map<String, ScheduleVO>> schedulList = (List<Map<String, ScheduleVO>>) request.getAttribute("schedulList");
%>


<script type="text/javascript">


var calendar = null;

$(document).ready(function() {
	
	 var Calendar = FullCalendar.Calendar;
	    var Draggable = FullCalendar.Draggable;

	    var containerEl = document.getElementById('external-events');
	    var calendarEl = document.getElementById('calendar');
	    var checkbox = document.getElementById('drop-remove');

	    // initialize the external events
	    // -----------------------------------------------------------------

// 	    new Draggable(containerEl, {
// 	      itemSelector: '.fc-event',
// 	      eventData: function(eventEl) {
// 	        return {
// 	          title: eventEl.innerText
// 	        };
// 	      }
// 	    });

	    // initialize the calendar
	    // -----------------------------------------------------------------

	    calendar = new Calendar(calendarEl, {
	      weekends: false,
	      headerToolbar: {
	        left: 'prev,next today', // 이전 다음 오늘 버튼
	        center: 'title', // 센터에 타이틀
	        right: 'dayGridMonth,timeGridWeek,timeGridDay' // 상단 월/주/일 버튼
	      },
	      editable: false, // 수정가능 여부 false = 수정 불가능
	      // 캘린더 안으로 드롭이 가능하다
	      droppable: false, // this allows things to be dropped onto the calendar
	      drop: function(info) {
	        // is the "remove after drop" checkbox checked?
	        // 클릭했을때 해당 객체를 제거하라
	        if (checkbox.checked) {
	          // if so, remove the element from the "Draggable Events" list
	          info.draggedEl.parentNode.removeChild(info.draggedEl);
	        }
	      },
	      locale : 'ko',
	      events : <%= jarr %>
	    }); 
		
	    calendar.render();
	    
	
	
	$('.selecClass').on('click',function(){
		vClass = $('#class_no option:selected').val().trim();
		console.log(vClass);
		location.href="<%= request.getContextPath()%>/schedule/ScheduleAdmin.do?classNo=" + vClass;
		
	})
	
	// 과목 추가버튼을 누르면
	$('.newschedule').on('click', function() {
		vschForm = $('#addscheduleForm').serialize();
		vclass = $('#classNo').val();
		console.log(" 해당 반 classNo=" , classNo);
		// 과목 이름 , 시작날짜 종료날짜
		vsub = $('#selectsub option:selected').val().trim();
		vsdate = $('#sdate').val();
		vedate = $('#edate').val();
		
		$.ajax({
			url : "<%= request.getContextPath()%>/schedule/ScheduleAdd.do",
			data : vschForm,
			type : 'post',
			success : function (res) {
				if(res.flag == "성공"){
					// location.reload();
					alert(<%= setclass %> + '반에 강의시간표를 추가했습니다.');
					code ="";
					code += "<tr class='sublist'><td><input type='hidden' class='schNo' value='" +  res.schNo +"'>"+  vsub + "</td>"
					code +=	"<td>" + vsdate + "</td>"
					code +=	"<td>" + vedate + "</td>"
					code +=	"<td><input type='button' value='삭제' class='btn btn-danger btn-sm delbtn'></td></tr>"
						
// 					$('#scheduletb tr').empty();	
					$('#scheduletb').append(code);	
					
				}
				
			},
			error : function(xhr) {
				alert('오류 : ' + xhr.status);
			},
			dataType : 'json'
		}); // ajax
		
	})
	
	// 시간표작성중 과목삭제
	$(document).on('click', '.delbtn', function() {
		// 이버튼을 누르면 해당하는 스케줄 번호 삭제해야함...
// 		vschNo = $('.schNo',this).val();
		
		vthis = this;
		vschNo = $(this).parents('.sublist').find('.schNo').val();
		console.log("삭제하려는 번호" + vschNo);
		
		$.ajax({
			url : '<%= request.getContextPath()%>/schedule/Scheduledelete.do',
			data : {"scheduleNO" : vschNo},
			type : 'post',
			success : function (res) {
				if(res.flag == "성공"){
					alert('강의시간표를 삭제했습니다.')
					$(vthis).parents('.sublist').remove(); // 선택한 tr을 지우기...
					
				}
			},
			error : function(xhr) {
				alert('오류 : ' + xhr.status);
			},
			dataType : 'json'
			
		});// ajax
		
	})
	// 모달창 닫기 누르면 reload
	$('.closeModal').on('click', function () {
		location.href="<%= request.getContextPath()%>/schedule/ScheduleAdmin.do?classNo=" + <%= setclass%>;
	})	
	
	
	    
});
	  
</script>
<style>

  html, body {
    margin: 0;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 20px auto;
    height: 700px;
    background-color: white;
  }
  
label{
	display: inline-block;
	width: 80px;
	margin: 5px;
}

.form-select{
	display: inline-block;
	width: 100px;
	margin-right: 5px;
}

</style>

<div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl">강의시간표 작성</div>
	    	 		
	    	 		
	    <div id='calendar-container'>
	    
	    <% if(setclass != null){
	    	out.print(" <input type='button' value='"+ setclass +"반 강의 시간표' class='btn btn-dark' disabled style='margin: 10px;'");
	    } %>
	  	<div class="btngroup">
	  		<select name="class_no" id="class_no" class="form-select">
	  			<% 
	  				for(ClassClassVO clist : classList) {
	  					if(setclass.equals(clist.getClass_no())){
	  				out.print("<option selected value='"+ clist.getClass_no()+"'>"
	  							+  clist.getClass_no() +"</option>");
	  					} else{
	  				out.print("<option value='"+ clist.getClass_no()+"'>"
	  							+  clist.getClass_no() +"</option>");
	  					}
	  			}
	  			%>
	  		</select><input type="button" value="학급선택" class="btn btn-primary selecClass">
	  		<input type="button" value="강의시간표 작성" class="btn btn-primary addSchedule"
	  		  data-bs-toggle="modal" data-bs-target="#addevent"></div><br><br>
	    <div id='calendar'></div>
	  </div>

     	 	</div>
    	 	</div>
    	 </div>
    </div>
  </div>	    
  
  
  
    <!-- The Modal -->
<div class="modal" id="addevent">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"><%= setclass %>반 강의추가</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        <form id="addscheduleForm">
        	<label>과&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;목 : </label>
       			<select name="title" id="selectsub">
       			<% 
       			for(ScheduleVO schlist : sublist){
       				out.print("<option value='"+ schlist.getTitle() +"'>" + schlist.getTitle() + "</option>") ;
       			}
       			%>
       			</select><br>
       		<label>시작날짜 :</label><input  type="text" placeholder="YYYY-MM-DD" name="start" id="sdate"><br>
       		<label>종료날짜 :</label><input type="text" placeholder="YYYY-MM-DD" name="end" id="edate"><br>
       		<input type="hidden" name="allday" value="true">
       		<input type="hidden" name="class_no" id="classNo" value="<%= setclass %>">
       		<div id="addList">
       			<table style='text-align: center; width : 400px;' id="scheduletb"><tr><td>과목</td><td>시작날짜</td><td>종료날짜</td><td></td></tr>
       			<%
	       			for (Map<String, ScheduleVO> map : schedulList) {
// 	       				out.print("<table style='text-align: center; width : 400px;'><tr><td>과목</td><td>시작날짜</td><td>종료날짜</td><td></td></tr>");
	       				out.print("<tr class='sublist'><td><input type='hidden' class='schNo' value='" + map.get("schedule_no") +"'>"+  map.get("title") + "</td>");
	       				out.print("<td>" + map.get("start") + "</td>");
	       				out.print("<td>" + map.get("end") + "</td>");
	       				out.print("<td><input type='button' value='삭제' class='btn btn-danger btn-sm delbtn'></td></tr>");
	       			}
       			%>
       			</table>
       		</div>
       		<br>
        	<input type="button" value="추가" class="btn btn-primary newschedule">
        	<input type="reset" value="취소" class="btn btn-primary cancel">
        </form>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-primary closeModal" data-bs-dismiss="modal">닫기</button>
      </div>

    </div>
  </div>
</div>
  
  
	    	 		