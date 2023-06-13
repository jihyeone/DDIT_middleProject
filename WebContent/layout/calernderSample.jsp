<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.0/index.global.min.js'></script>
    <script>

      document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
          initialView: 'dayGridMonth'
        });
        calendar.render();
      });

    </script>
    <style>
      #calendar{
        width: 480px;
        height: 470px;
        background-color: white;
        overflow: hidden;
      }

    </style>
</head>
<body>
 <div id='calendar'></div>
</body>
</html>