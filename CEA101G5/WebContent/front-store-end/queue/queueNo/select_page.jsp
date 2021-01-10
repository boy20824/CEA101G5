<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<html>
<head>
<title>queueNoSelectPage</title>

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
   <tr><td><h3>queueNoSelectPage</h3><h4>( MVC )</h4></td></tr>
</table>

<p>queueNoSelectPage</p>

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
  <li><a href='listAllQueNo.jsp'>List</a> all queueNO  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="queueNo.do" >
        <b>queueNO</b>
        <input type="text" name="queno">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="queNoSvc" scope="page" class="com.queueno.model.QueNoService" />
 <jsp:useBean id="storeSvc" scope="page" class="com.restaurant.model.RestaurantService" />
   
  <li>
     <FORM METHOD="post" ACTION="queueNo.do" >
       <b>storeid:</b>
       <select size="1" name="storeid">
         <c:forEach var="storeVO" items="${storeSvc.all}" > 
          <option value="${storeVO.storeId}">${storeVO.storeId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="list_One_Store">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="queueNo.do" >
       <b>queuenum:</b>
       <select size="1" name="queuenum">
         <c:forEach var="queNoVO" items="${queNoSvc.all}" > 
          <option value="${queNoVO.queuenum}">${queNoVO.queuenum}
         </c:forEach>   
       </select>
       <b>phone:</b>
       <select size="1" name="memphone">
         <c:forEach var="queNoVO" items="${queNoSvc.all}" > 
          <option value="${queNoVO.memphone}">${queNoVO.memphone}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="queueNo.do" >
       <b>memphone:</b>
       <select size="1" name="memphone">
         <c:forEach var="queNoVO" items="${queNoSvc.all}" > 
                   <option value="${queNoVO.memphone}">${queNoVO.memphone}</option>
         </c:forEach>   
       </select>
       <input type="hidden" name="queuenum" value="0">
       <input type="hidden" name="action" value="getOne_By_Phone">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="queueNo.do" >
       <b>我要取號:</b> 
       <input type="hidden" name="storeid" value="S000001">
       <input type="hidden" name="action" value="getQueNo">       
       <input type="submit" value="送出">
     </FORM>
  </li>
  <li>
     <FORM METHOD="post" ACTION="queueNo.do" >
       <b>餐廳取號:</b> 
       <input type="hidden" name="storeid" value="S000001">
       <input type="hidden" name="action" value="storeGetQueNo">       
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addQueNo.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>