<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.acceptreserve.model.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	AcceptReserveService arSvc = new AcceptReserveService();
    List<AcceptReserveVO> list = arSvc.getSearch(((RestaurantVO)(session.getAttribute("storeLogin"))).getStoreId());
    pageContext.setAttribute("list",list);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String today = sdf.format(new Date());
    Long tl = new Date().getTime();
    String tomorrow = sdf.format(new Date(tl + (24*60*60*1000L)));
    
    AcceptReserveVO arVO = (AcceptReserveVO) request.getAttribute("arVO");
    String ss = "S000003";
    pageContext.setAttribute("ss",ss);
%>


<html>
<head>
<title>所有訂位時段資料 - listAllAcceptReserve.jsp</title>

<style>
  table#table-1 {
	background-color: #f3853d;
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
 	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	text-align:center;
  }
  table, th, td {
     border: 1px solid #404040; 
     border-spacing: 0; 
     background-color: #F6F6F6;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  .button{
	  background-color:#FF615F;
	  border-radius: 5px;
	  border:1px;
	  color:white;
	  font-family: #606060;
	  text-decoration:none;
  }
  body{
  	magin:0;
  	padding:0;
  	text-align:center;
/*   	background-image: url("images/r1.jpg"); */
  	background-size: cover;
  	background-attachment: fixed;
  	background-position: center;
  	background-repeat: no-repeat;
  }
  .info{
  	text-align: center;
  	margin: 50px auto;
  }
  .tableborder{
  background-color: white;
  margin:20px;
  }
  #openp{
  background-color: #228b22;
  }

</style>

</head>
<body bgcolor='white'>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
			${message}
		</c:forEach>
</c:if>
<br>
<table class="info">
	<caption>所有時段</caption>
	<tr>
		<th>時段編號</th>
		<th>開始時間</th>
		<th>結束時間</th>
		<th>訂位時段狀態</th>
		<th>修改狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="arVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${arVO.periodId}</td>
			<td><fmt:formatDate value="${arVO.startTime}" pattern="HH:mm"/></td>
			<td><fmt:formatDate value="${arVO.endTime}" pattern="HH:mm"/></td>
			<td>
				<c:if test="${arVO.periodStatus==0}">
					<span>關閉中</span>
				</c:if>
				<c:if test="${arVO.periodStatus==1}">
					<span>開放中</span>
				</c:if>
			</td> <!-- JSP會自動幫你setAttribute EL會自動幫你getAttribute -->
										 <!-- 如果要用jsp寫法java.text.DateFormat 要先setAttribute 再get很麻煩 -->
										 <!-- 直接DateTimePicker的formatDate  然後上面要加 tablib fmt 1210 11有講 -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/acceptreserve/acceptreserve.do" style="margin-bottom: 0px;">
			     <c:if test="${arVO.periodStatus==0}">
					<input type="submit" value="開啟" class="button" id="openp">
					<input type="hidden" name="periodstatus"  value=1>
					<input type="hidden" name="delperiod" value="open">
					<input type="hidden" name="reservesituationdate" value="<%=tomorrow %>">
					<input type="hidden" name="acceptdays" value="${storeLogin.storeFinalReservDate}"><!-- 這個允許訂位天數的名稱我亂取的  還要跟餐廳拿 -->
					<input type="hidden" name="acceptgroups" value="${storeLogin.acceptGroups}"><!-- 這個也要跟餐廳拿 -->
					<input type="hidden" name="reservedgroups" value=0>
				</c:if>
				<c:if test="${arVO.periodStatus==1}">
					<input type="submit" value="關閉" class="button" id="closep">
					<input type="hidden" name="periodstatus" value=0>
					<input type="hidden" name="delperiod" value="close">
					<input type="hidden" name="reservesituationdate" value="<%=today %>">
				</c:if>
			     
			     <input type="hidden" name="storeid"  value="${arVO.storeId}"><!-- 這個值是傳給getOne for update去修改所以我應該要傳兩個? -->
			     <input type="hidden" name="periodid"  value="${arVO.periodId}">
			     <input type="hidden" name="starttime"  value="${arVO.startTime}">
			     <input type="hidden" name="endtime"  value="${arVO.endTime}">
			     <input type="hidden" name="action"	value="updateperiod"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/reserveorder/reserveorder.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="reserveid"  value="${reserveOrderVO.reserveId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/acceptreserve/acceptreserve.do" name="form1">
<table class="info">
	<tr>
		<th>開始時間</th>
		<th>結束時間</th>
	</tr>
		<jsp:useBean id="arSvc2" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
		<c:if test="${arSvc2.getSearch(storeLogin.storeId).size() == 0}">
			<input type="hidden" name="periodid" value=1>
		</c:if>
		<c:if test="${arSvc2.getSearch(storeLogin.storeId).size() > 0}">
			<input type="hidden" name="periodid" value="${arSvc2.getSearch(storeLogin.storeId).size()+1}">
		</c:if>
	
	<tr>
		<input type="hidden" name="storeid" value="${storeLogin.storeId}"> <!-- 引入餐廳ID -->
		<td><input name="starttime" id="f_date1" type="text"></td>
		<td><input name="endtime" id="f_date2" type="text"></td>
		<input type="hidden" name="periodstatus" value=1>
	</tr>

	</table>
	<c:forEach var="arVO" items="${arSvc2.getSearch(storeLogin.storeId)}">
		<input type="hidden" name="${arVO.periodId}" value="${arVO.startTime}">
		<input type="hidden" name="${arVO.periodId*10}" value="${arVO.endTime}">
	</c:forEach>
	<input type="hidden" name="fori" value="${arSvc2.getSearch(storeLogin.storeId).size()}">	
<input type="hidden" name="action" value="insert">



<input type="hidden" name="reservesituationdate" value="<%=tomorrow %>">
<input type="hidden" name="acceptdays" value="${storeLogin.storeFinalReservDate}"><!-- 這個允許訂位天數的名稱我亂取的  還要跟餐廳拿 -->
<input type="hidden" name="acceptgroups" value="${storeLogin.acceptGroups}"><!-- 這個也要跟餐廳拿 -->
<input type="hidden" name="reservedgroups" value=0>
<input type="submit" value="新增時段" class="button"></FORM>
<% 
  java.sql.Timestamp starttime = null;
  try {
	  starttime = arVO.getStartTime();
   } catch (Exception e) {
	   starttime = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp endtime = null;
  try {
	  endtime = arVO.getEndTime();
   } catch (Exception e) {
	   endtime = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
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
$('#f_date1').datetimepicker({
   theme: '',              //theme: 'dark',
   datepicker:false,
   timepicker:true,       //timepicker:true,
   step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   value: '<%=starttime%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   startDate:	            '2000/01/01',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});   
$.datetimepicker.setLocale('zh');
$('#f_date2').datetimepicker({
   datepicker:false,
   theme: '',              //theme: 'dark',
   timepicker:true,       //timepicker:true,
   step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
   value: '<%=endtime%>', // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   startDate:	            '2000/01/01',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

//      1.以下為某一天之前的日期無法選擇
     var somedate1 = new Date();
	 var somedate2 = new Date()
// //下面區塊為+幾天的宣告法
     Date.prototype.addDays = function(days) {
    	  this.setDate(this.getDate() + days);
    	  return this;
    	}
	 somedate1.addDays(1);
// 	 somedate2.addDays(3);
//
     $('#f_date1').datetimepicker({
         beforeShowDay: function(date) {
       	  if (  date.getYear() <  somedate1.getYear() || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
		           || (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
		           || (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth())
		           || date.getYear() >  somedate2.getYear() 
       	  ) {
                  return [false, ""]
             }
             return [true, ""];
     }});

     $('#f_date2').datetimepicker({
         beforeShowDay: function(date) {
       	  if (  date.getYear() <  somedate1.getYear() || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
		           || (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
		           || (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth())
		           || date.getYear() >  somedate2.getYear()
       	  ) {
                  return [false, ""]
             }
             return [true, ""];
     }});
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }

</script>
</body>
</html>