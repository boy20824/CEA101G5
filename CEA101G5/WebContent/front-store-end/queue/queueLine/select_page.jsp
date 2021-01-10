<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM queLine: Home</title>

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
   <tr><td><h3>IBM queLine: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM queLine: Home</p>

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
  <li><a href='listAllQueLine.jsp'>List</a> all QueLine.  <br><br></li>
  
  
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="queueLine.do" > -->
<!--         <b>輸入queLine:</b> -->
<!--         <input type="text" name="queuelineno"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->

  <jsp:useBean id="queLineSvc" scope="page" class="com.queueline.model.QueLineService" />
  <jsp:useBean id="restSvc" scope="page" class="com.restaurant.model.RestaurantService" />
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="queueLine.do" > -->
<!--        <b>storeid:</b> -->
<!--        <select size="1" name="empno"> -->
<%--          <c:forEach var="empVO" items="${empSvc.all}" >  --%>
<%--           <option value="${empVO.empno}">${empVO.empno} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
  <li>
     <FORM METHOD="post" ACTION="queueLine.do" >
       <b>storeid:</b>
       <select size="1" name="storeid">
         <c:forEach var="restVO" items="${restSvc.all}" > 
          <option value="${restVO.storeId}">${restVO.storeId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="list_One_Store">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增queline</h3>

<ul>
  <li><a href='addQueLine.jsp'>Add</a> a new queline.</li>
</ul>

</body>
</html>