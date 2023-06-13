<%@page import="kr.or.ddit.middle.vo.StudentVO"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.or.ddit.middle.vo.ScheduleVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
        	List<Map<String, ScheduleVO>> smap = (List<Map<String, ScheduleVO>>) request.getAttribute("maplist");
        	JSONArray jarr = (JSONArray) request.getAttribute("jsonArr");
        	StudentVO svo = (StudentVO) request.getAttribute("svo");
        	
        	System.out.println("여기는 jsp에서 넘어온 값입니다.-----------------------");
        	System.out.println("jarr =>" + jarr);
        	System.out.println("smap => " + smap);
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
	    
	    
// 전체 이벤트 데이터 추출해서 ajax에 보낸다.

// 	$('#savebtn').on('click', function() {
// 		// calendar.getEvents() 모든 정보 array로 가져옴
// 		allevent =  calendar.getEvents();
// 		console.log(allevent);
// 		// 가져올것..
// 		// allDay : true = 하루종일 진행되는 이벤트
// 		// allDay : false = 특정한 날 특정 시간때
// 		// instance : range - end / start
		
// 		var  events = new Array(); // 배열생성하고 push로 obj객체 저장
		
// 		for(var i = 0; i < allevent.length; i ++){
			
// 			var obj = new Object(); // 객체
			
// 			obj.title = allevent[i]._def.title; // 이벤트 명칭 일정 제목 띄우기
// 			obj.allday = allevent[i]._def.allDay; // 하루종일의 이벤트인지 true/false
// 			obj.start = allevent[i]._instance.range.start; // 시작 날짜 및 시간
// 			obj.end = allevent[i]._instance.range.end; // 종료날짜 및 시간
			
// 			events.push(obj); // for문을 돌면서 이벤트들이 배열 형태로 json객체 형태로 저장
// 		}
		
// 		// 문자열 형태로전달하기 위해
// 		var jsondata = JSON.stringify(events);
// 		console.log(jsondata);
		
// 		savedata(jsondata);
		
		
// 	})
	
// 	function savedata(jsondata) {
		
// 	$.ajax({
<%-- 		url : '<%= request.getContextPath()%>/savedatadata.do', --%>
// 		type : 'post',
// 		data : {"alldata" : jsondata },
// 		dataType : 'text',
// 		async : false // 동기식으로 넘기기
		
// 	})
// 	.done(function(res) {
		
// 	}) //성공했을때
// 	.fail(function (request, status, error) {
// 		alert("에러 발생 : " + error);
// 	}) // 실패했을때
// }

	    
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
    height: 650px;
    background-color: white;
  }
.fc-event{
	margin: 5px;
	cursor : move;
}
</style>


    <div class="container-content">
        <div class="row">
            <div class="row col-md-12">
                <div class="frame content">
                    <div class="stumanage">
	    	 		<div class="title stuttl"><%= svo.getClass_no() %>반 강의시간표</div>
	    	 		

	    	 		
	    <div id='calendar-container'>
	    
	    <!-- 캘린더 영역 -->
	    <div id='calendar'></div>
	    
			 		 </div>
     	 		</div>
    	 	</div>
    	 </div>
    </div>
  </div>	    
  
  
  