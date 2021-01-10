<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM queperiod: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM queperiod: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Emp: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllQuePeriod.jsp'>List</a> all queperiod.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="queuePeriod.do" >
        <b>periodid:</b>
        <input type="text" name="queueperiodid">
        <b>storeid</b>
        <input type="text" name="storeid">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="quePeriodSvc" scope="page" class="com.queueperiod.model.QuePeriodService" />
   
  <li>
     <FORM METHOD="post" ACTION="queuePeriod.do" >
       <b>periodid</b>
       <select size="1" name="queueperiodid">
         <c:forEach var="quePeriodVO" items="${quePeriodSvc.all}" > 
          <option value="${quePeriodVO.queueperiodid}">${quePeriodVO.queueperiodid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <b>storeid</b>
       <select size="1" name=storeid>
         <c:forEach var="quePeriodVO" items="${quePeriodSvc.all}" > 
          <option value="${quePeriodVO.storeid}">${quePeriodVO.storeid}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="storeSvc" scope="page" class="com.restaurant.model.RestaurantService"/>
  <li>
     <FORM METHOD="post" ACTION="queuePeriod.do" >
       <b>商店時段</b>
       <select size="1" name="storeid">
         <c:forEach var="storeVO" items="${storeSvc.all}" > 
        
          <option value="${storeVO.storeId}">${storeVO.storeId}</option>
         </c:forEach>
       </select>
       <input type="hidden" name="action" value="getOne_Store">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addQuePeriod.jsp'>Add</a> 新增時段</li>
</ul>

</body>
</html>