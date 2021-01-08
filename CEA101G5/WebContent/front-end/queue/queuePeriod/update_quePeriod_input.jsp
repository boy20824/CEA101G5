<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.queueperiod.model.*"%>

<%
  QuePeriodVO quePeriodVO = (QuePeriodVO) request.getAttribute("quePeriodVO"); //EmpServlet.java (Concroller) 存入req的quePeriodVO物件 (包括幫忙取出的quePeriodVO, 也包括輸入資料錯誤時的quePeriodVO物件)
%>
<%= quePeriodVO==null %>--${quePeriodVO.storeid}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title> update_quePeriod_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>update_quePeriod_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
		 <h4><a href="Que_Period_Setting.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回設定頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="quePeriod.do" name="form1">
<table>
	<tr>
		<td>queperiodid:<font color=red><b>*</b></font></td>
		<td><%=quePeriodVO.getQueueperiodid()%></td>
	</tr>
	<tr>
		<td>店:</td>
		<td><input type="TEXT" name="storeid" size="45" value="<%=quePeriodVO.getStoreid()%>" /></td>
	</tr>
	<tr>
		<td>st:</td>
		<td><c:choose>
		<c:when test="${quePeriodVO.queuest == 1}">
		<input type="radio" id="open" name="queuest" value="1" checked="checked">
  <label for="open">Open</label>
  <input type="radio" id="close" name="queuest" value="0">
  <label for="close">Close</label><br>
  </c:when>
		<c:otherwise>
		<input type="radio" id="open" name="queuest" value="1">
  <label for="open">Open</label>
  <input type="radio" id="close" name="queuest" value="0" checked="checked">
  <label for="close">Close</label><br>
  </c:otherwise></c:choose></td>
	</tr>
	<tr>
		<td>start日期:</td>
		<td><input name="queuestarttime" class="s_date" id="f_date1" type="text" value="<%=quePeriodVO.getQueuestarttime()%>"></td>
	</tr>
	<tr>
		<td>end日期:</td>
		<td><input name="queueendtime" class="e_date" id="f_date2" type="text" value="<%=quePeriodVO.getQueueendtime()%>"></td>
	</tr>
	<tr>
		<td>nocurrent:</td>
		<td><input type="TEXT" name="queuenocurrent" size="45"	value="<%=quePeriodVO.getQueuenocurrent()%>" /></td>
	</tr>
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.queueperiod.model.QuePeriodService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(quePeriodVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="queueperiodid" value="<%=quePeriodVO.getQueueperiodid()%>">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('.s_date').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       timeFormat:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
 		   value: '<%=quePeriodVO.getQueuestarttime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        $.datetimepicker.setLocale('zh');
        $('.e_date').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       timeFormat:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
 		   value: '<%=quePeriodVO.getQueueendtime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>