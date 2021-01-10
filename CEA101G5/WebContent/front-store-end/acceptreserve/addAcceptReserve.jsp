<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acceptreserve.model.*"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
  AcceptReserveVO arVO = (AcceptReserveVO) request.getAttribute("arVO");
String ss = "S000003";
pageContext.setAttribute("ss",ss);
Long tl = new Date().getTime();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String nextDay = sdf.format(new Date(tl + (24*60*60*1000L)));

%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增訂位時段</title>

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
  tr td {
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

<a href="<%=request.getContextPath() %>/front-store-end/acceptreserve/listAllAcceptReserve.jsp" class="button">回首頁</a>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
		<c:forEach var="message" items="${errorMsgs}">
${message}
		</c:forEach>
</c:if>
<!-- ACITION最好都加&GT=request.getContextPath() %%LT> -->
<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/acceptreserve/acceptreserve.do" name="form1">
<table class="info">
	<tr>
		<th>開始時間</th>
		<th>結束時間</th>
	</tr>
		<jsp:useBean id="arSvc" scope="page" class="com.acceptreserve.model.AcceptReserveService"/>
		<c:if test="${arSvc.getSearch(ss).size() == 0}">
			<input type="hidden" name="periodid" value=1>
		</c:if>
		<c:if test="${arSvc.getSearch(ss).size() > 0}">
			<input type="hidden" name="periodid" value="${arSvc.getSearch(ss).size()+1}">
		</c:if>
	
	<tr>
		<input type="hidden" name="storeid" value="S000003"> <!-- 引入餐廳ID -->
		<td><input name="starttime" id="f_date1" type="text"></td>
		<td><input name="endtime" id="f_date2" type="text"></td>
		<input type="hidden" name="periodstatus" value=1>
	</tr>

	</table>
	<c:forEach var="arVO" items="${arSvc.getSearch(ss)}">
		<input type="hidden" name="${arVO.periodId}" value="${arVO.startTime}">
		<input type="hidden" name="${arVO.periodId*10}" value="${arVO.endTime}">
	</c:forEach>
	<input type="hidden" name="fori" value="${arSvc.getSearch(ss).size()}">	
<input type="hidden" name="action" value="insert">



<input type="hidden" name="reservesituationdate" value="<%=nextDay %>">
<input type="hidden" name="acceptdays" value=7><!-- 這個允許訂位天數的名稱我亂取的  還要跟餐廳拿 -->
<input type="hidden" name="acceptgroups" value=6><!-- 這個也要跟餐廳拿 -->
<input type="hidden" name="reservedgroups" value=0>
<input type="submit" value="新增時段" class="button"></FORM>

</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

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
           //startDate:	            '2017/07/10',  // 起始日
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
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
             var somedate1 = new Date();
        	 var somedate2 = new Date()
//         //下面區塊為+幾天的宣告法
             Date.prototype.addDays = function(days) {
            	  this.setDate(this.getDate() + days);
            	  return this;
            	}
        	 somedate1.addDays(1);
//         	 somedate2.addDays(3);
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

        
//              2.以下為某一天之後的日期無法選擇
//              var somedate2 = new Date();
//              somedate2.addDays(10);
//              $('#f_date1').datetimepicker({
//                  beforeShowDay: function(date) {
//                	  if (  date.getYear() >  somedate2.getYear() || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
//         		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
//                      ) {
//                           return [false, ""]
//                      }
//                      return [true, ""];
//              }});


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
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
        
</script>
</html>